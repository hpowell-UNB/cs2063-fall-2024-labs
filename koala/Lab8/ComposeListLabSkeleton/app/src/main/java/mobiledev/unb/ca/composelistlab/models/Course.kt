package mobiledev.unb.ca.composelistlab.models

import android.os.Parcel
import android.os.Parcelable

/**
 * This makes use of the data class pattern
 *  All fields are public
 *  title is a combination result of id and name
 */
data class Course(val id: String?,
                  val name: String?,
                  val description: String?): Parcelable {
    // Also include a getter for the course title to be used in the scrolling list
    val title: String
        get() = "$id: $name"

    // Parcelable allows for the entire object to be
    // passed through as an Intent extra
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }
}

fun testCourse(): Course {
    return Course(
        id = "CS2063",
        name = "Introduction to Mobile Application Development 4ch",
        description = "Introduces students to the development of application software for mobile computing platforms.  Characteristics of mobile computing platforms versus non-mobile platforms. Mobile application design principles, including design of effective user interaction and factors that affect application performance. Programming common mobile application functionality such as location, orientation, and motion awareness, as well as touch, gesture, and camera input. Interacting with remote APIs (e.g. Google Maps). Students will gain experience creating and testing applications for a selected currently prominent mobile platform.Prerequisite(s): CS 2043 or CS 2253 ."
    )
}

fun dummyData(): List<Course> {
    return listOf(testCourse())
}
