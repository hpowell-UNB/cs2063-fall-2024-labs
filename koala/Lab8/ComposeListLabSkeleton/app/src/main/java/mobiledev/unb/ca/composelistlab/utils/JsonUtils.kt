package mobiledev.unb.ca.composelistlab.utils

import android.content.Context
import mobiledev.unb.ca.composelistlab.models.Course
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class JsonUtils(context: Context) {
    // Course array only available through a getter
    var courses: ArrayList<Course> = ArrayList()
        private set

    private fun processJSON(context: Context) {
        try {
            // Create a JSON Object from file contents String
            val jsonObject = JSONObject(Objects.requireNonNull(loadJSONFromAssets(context)))

            // Create a JSON Array from the JSON Object
            // This array is the "courses" array mentioned in the lab write-up
            val jsonArray = jsonObject.getJSONArray(KEY_COURSES)
            for (i in 0 until jsonArray.length()) {
                // Create a JSON Object from individual JSON Array element
                val elementObject = jsonArray.getJSONObject(i)

                // Get data from individual JSON Object
                val course = Course(elementObject.getString(KEY_COURSE_ID),
                    elementObject.getString(KEY_NAME),
                    elementObject.getString(KEY_DESCRIPTION))

                // Add new Course to courses ArrayList
                courses.add(course)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAssets(context: Context): String? {
        return try {
            context.assets.open(CS_JSON_FILE)
                .bufferedReader()
                .use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    companion object {
        private const val CS_JSON_FILE = "CS.json"
        private const val KEY_COURSES = "courses"
        private const val KEY_COURSE_ID = "courseID"
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
    }

    // Initializer to read our data source (JSON file) into an array of course objects
    init {
        processJSON(context)
    }
}