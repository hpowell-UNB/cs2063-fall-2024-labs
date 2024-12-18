# Lab Exam

This lab exam is open everything (Android documentation, labs and examples, StackOverflow, Google, etc.).

**You must not communicate with others (in person or online) during the exam.**

# Summer Olympics Host Games app

This year the latest installment of the [Summer Olympic Games](https://en.wikipedia.org/wiki/Summer_Olympic_Games) were held in Paris, France.  We will celebrate this occasion by building an app that displays information about all of the summer games that have taken place in the past.

This app will consist of two activities. The main activity will include a `RecyclerView` where the items are the Olympiad number and host city name along with checkboxes that the user can check when they have seen an item.

Clicking on the name of a host city will take the user to a detail activity
that shows the extra details about the particular games (Year of the games, dates) along with a button to view the Wikipedia entry for more information on the games themselves. Pressing back from the detail activity takes the user back to the main activity. See examples below.

The instructor has Android devices with a sample solution installed. You can ask to briefly borrow one of these devices to see the intended functionality.

### Main activity:

![Main Activity](main-activity.png)

### Detail activity:

![Detail Activity](detail-activity.png)

## Skeleton Code

You have been provided with skeleton code. The structure of the app
will be familiar to you from previous labs.

* `EventInfo` represents an Olympic event
  * Data class with standard constructor and a single getter method to retrieve the event number and city are provided
  
* `JsonUtils` loads the details from the JSON assets file
  * The app will make use of the contents included in an included asset file

* `DetailActivity` corresponds to the second screenshot above, displaying information about a specific host city
  * It doesn't do much yet as you will be completing it

* `MainActivity` will display a list of names of host nations displayed in a `RecyclerView`
  * `MainActivity` will use `SharedPreferences` to store information about which games the user has visited
  
* `MyAdapter` corresponds to the RecyclerView class being used to populate the view with games information.
  * When the name of a games is clicked the `DetailActivity` will be launched via an explicit `Intent`

* `LoadDataTask` is used to read in the details and updated the view using a separate thread

## Lab Tasks

Complete the TODOs in `MainActivity.kt`, `DetailActivity.kt`, `LoadDataTask.kt`, and `MyAdapter.kt`.

NOTES: 
* There are also TODOs related to the `SharedPreferences` which are labeled as `TODO: SharedPreferences`
* You will need to pass around an instance of a `SharedPreferences` object to be used throughout.  
  * The keys will be a string value for the Olympic event number associated with a host nation and the values will be a boolean.
    * True representing that a user has visited a nation and false representing that a user has not
    * If an id is not found in the `SharedPreferences` its value will be assumed to be false

**You may only modify `MainActivity.kt` and `DetailActivity.kt`, `LoadDataTask.kt`, and `MyAdapter.kt`. You must not make changes to any other files.**

If you think you need to modify any other file, ask the
instructor or TA.

## Hints

Here are links to some documentation that might be helpful. 

### MainActivity

* <https://developer.android.com/reference/android/widget/CheckBox.html>

* <https://developer.android.com/reference/android/widget/CompoundButton.html#setChecked(boolean)>

* <https://developer.android.com/reference/android/content/SharedPreferences.html#getBoolean(java.lang.String,%20boolean)>

* <https://developer.android.com/reference/android/content/SharedPreferences.html#edit()>

* <https://developer.android.com/reference/android/content/SharedPreferences.Editor.html#putBoolean(java.lang.String,%20boolean)>

* <https://developer.android.com/reference/android/content/SharedPreferences.Editor.html#apply()>

### DetailActivity

* <https://developer.android.com/guide/components/intents-common.html#Browser>

* <https://developer.android.com/reference/android/net/Uri.html#parse(java.lang.String)>

* <https://developer.android.com/reference/android/content/Intent.html#resolveActivity(android.content.pm.PackageManager)>


## Deliverables

When you are done, submit `MainActivity.kt` and `DetailActivity.kt`, `LoadDataTask.kt`, and `MyAdapter.kt`to the LabExam Dropbox folder on D2L.

You can find these files in the directory at this relative path: `LabExamSkeleton/app/src/main/java/mobiledev/unb/ca/labexam/`


## Final Words

Good luck, and have fun!

If you get stuck ask a question. This is an exam so the instructor and TA can only provide limited answers. However, depending on the nature of the problem they might be able to help.

In any case submit your best effort. Your code will be read when it is marked, so it is possible to do well on this exam even if your code does not run correctly.
