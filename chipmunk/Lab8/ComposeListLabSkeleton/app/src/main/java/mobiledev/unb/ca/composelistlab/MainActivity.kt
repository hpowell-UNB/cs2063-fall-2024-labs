package mobiledev.unb.ca.composelistlab

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import mobiledev.unb.ca.composelistlab.models.Course
import mobiledev.unb.ca.composelistlab.models.dummyData
import mobiledev.unb.ca.composelistlab.ui.theme.ComposeListLabSkeletonTheme
import mobiledev.unb.ca.composelistlab.utils.JsonUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListLabSkeletonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent(testMode: Boolean = false) {
    val context = LocalContext.current
    val courseListing = when (testMode) {
        true -> dummyData()
        false -> JsonUtils(context).courses
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        context.getString(R.string.app_name),
                        color = Color.White
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = { MainActivityContent(courseListing = courseListing) }
    )
}

@Composable
fun MainActivityContent(courseListing: List<Course>) {
    // Temporarily show a single element; we will work on showing the entire list
    CourseItem(courseListing.first())

    // TODO: Replace single element with a scrolling list
    //  HINTS:
    //     Use each Course element in the course listing array to populate the view
    //     Take a look at using a LazyColumn (additional information can be found
    //     here - https://developer.android.com/develop/ui/compose/lists#lazy)
}

@Composable
fun CourseItem(course: Course) {
    // TODO 1: Create a row and show the course title using a Text object
    //    and apply basic styling.
    //  HINTS:
    //    Row class - https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#Row(androidx.compose.ui.Modifier,androidx.compose.foundation.layout.Arrangement.Horizontal,androidx.compose.ui.Alignment.Vertical,kotlin.Function1)
    //    Text class - https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Text(androidx.compose.ui.text.AnnotatedString,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.font.FontStyle,androidx.compose.ui.text.font.FontWeight,androidx.compose.ui.text.font.FontFamily,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextDecoration,androidx.compose.ui.text.style.TextAlign,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextOverflow,kotlin.Boolean,kotlin.Int,kotlin.Int,kotlin.collections.Map,kotlin.Function1,androidx.compose.ui.text.TextStyle)
    //    Styling is done using the Modifier class - https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier

    // TODO 2: Make the row clickable
    //  Option 1 (clickable modifier option) - https://developer.android.com/develop/ui/compose/modifiers
    //                                         and https://composables.com/foundation/clickable
    //  Option 2 (ClickableText - deprecated) - https://developer.android.com/reference/kotlin/androidx/compose/foundation/text/package-summary#ClickableText(androidx.compose.ui.text.AnnotatedString,androidx.compose.ui.Modifier,androidx.compose.ui.text.TextStyle,kotlin.Boolean,androidx.compose.ui.text.style.TextOverflow,kotlin.Int,kotlin.Function1,kotlin.Function1)

    // TODO - Optional 1: Wrap each row inside a Card
    //  Class documentation - https://developer.android.com/develop/ui/compose/components/card

    // TODO - Optional 2: Include the image from R.drawable.course_iconfinder
    //  Class documentation - https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary#Image
}

private fun goToDetailActivity(course: Course, context: Context) {
    val intent = Intent(context, DetailActivity::class.java).apply {
        putExtra(Constants.INTENT_EXTRA_COURSE_OBJECT, course)
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Log.e("MainActivity", "Unable to start activity", e)
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ComposeListLabSkeletonTheme {
        // TODO: Set the testMode to false when the scrolling list
        //   has been completed
        MainContent(testMode = true)
    }
}