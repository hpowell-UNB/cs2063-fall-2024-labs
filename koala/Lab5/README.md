# Lab 5 - Threading and Scrolling Lists

In class we have seen the importance of moving long-running operations off of the UI thread and into a worker thread. In this lab you will implement (portions of) this functionality using a runnable thread using an `Executor`.

## Pre-lab reading

This lab makes use of `RecyclerView` to show a list of items.  Please take some time to read the following documentation [here](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)

## Pair Programming

We will again be doing pair programming for this lab.  Details on pair programming can be found at [Pair Programming](../../docs/PAIR_PROGRAMMING.md).  You can again work with anybody of your choosing.


## Introduction

Many mobile applications need to display a scrollable list of items where the user can select an item to take some action specific to it. In this lab you will build an app that displays a scrolling list of UNB CS courses (including course numbers, titles and credit hours).

![Main Activity](https://i.imgur.com/8vQZmXf.png?1)

Selecting a course from the list of courses in the main activity takes the user to a detail activity showing the description for the selected course. (Yes, the course information we're using in this lab is a bit out of date)

![Detail Activity](https://i.imgur.com/qaqnSXb.png?1)

There are two common ways to display a scrolling list of items in Android
1. [`ListView`](https://developer.android.com/guide/topics/ui/layout/listview.html)
2. [`RecyclerView`](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)

The `RecyclerView` provides greater flexibility and will be the focus today

## Understanding the Skeleton Code

**Task 1**

1. Importing the Lab's skeleton project.  Note the following classes for use:
    * `Course` represents a course
      * Data class with standard constructor and a single getter method to retrieve the course title and description are provided

    * `JsonUtils` is a class used to work with ```JSON``` files
      * The app will make use of the contents included in an included asset file (```assets/CS.json```) which contains information about Computer Science courses available at UNB
      * ```JsonUtils``` processes the ```JSON``` to create ```Course``` items
        * Note in particular the ```JsonUtils``` constructor and the argument it takes along with the  ```JsonUtils.getCourses()``` function

    * `LoadDataTask` is a class used to perform data loading in a separate thread
  
    * `Constants` object class containing the key names for the Intent extra values used between activities
  
    * `DetailActivity` corresponds to the second screenshot above, displaying information about a specific course
      * It doesn't do much yet as you will be completing it during the lab

    * `MainActivity` presents the scrolling list of courses using a `RecyclerView`
      * NOTE: For this lab we will be using an indefinite circular progress bar meaning it does not need have its progress updated.  We will look at using a different style progress bar in a later lab.

    * `MyAdapter` corresponds to the RecyclerView class being used to populate the view with course information.
      * This class also contains an inner class called `MyAdapter` represents the custom adaptor class used by the RecyclerView which extends `RecyclerView.Adapter`
        * The `RecyclerView.Adapter` class provides a layer of abstraction between the `RecyclerView`'s `LayoutManager` and the underlying data that is being displayed which in this case a list of `Course` objects
        * `MyAdapter` itself contains an inner class `ViewHolder` which represents an individual item to display in the scrolling list
          * `onCreateViewHolder` creates `ViewHolder` objects by inflating the corresponding XML layout resource file; it's already implemented for you
          * `onBindViewHolder` will be called when a particular item in the dataset needs to be displayed in the scrolling list, i.e., the user has scrolled and a new item comes into view
          * This method sets up the `ViewHolder` to display the corresponding item in the dataset.
            * It is incomplete; you will finish it below.
      * When the name of a course is clicked the `DetailActivity` will be launched via an explicit `Intent`


2. Take note of the layout files  
    * `activity_detail.xml` and `activity_main.xml` are layouts for `DetailActivity` and `MainActivity` respectively
    * You can verify this by looking at the calls these classes make to `setContentView()`
    * `activity_main.xml` includes a `RecyclerView` where it's `LayoutManager` has been set to a `LinearLayoutManager` to give a list of items
      * This differs from a `GridLayoutManager` which would present the items in a grid
    * `item_layout.xml` will be used to display each item in the scrolling list in `MainActivity` and contains just a single `TextView`

## Implementation

**Task 2**

Complete the TODOs in the `JsonUtils`, `LoadDataTask`, `DetailActivity`, `MainActivity`, and `MyAdapter` files.

NOTE:
* The `ActionBar` corresponds to the text at the top of the detail activity in the screen shot above (i.e., "CS2063 Introduction to M...")

**Task 3**

On a smaller device (or for a very long course description) all of the text won't fit on the screen so we need to be able to scroll the text. You will also notice that there is no vertical scroll bar when the content is too long.

1. Update one of the course descriptions in the CS.json file to ensure that it is long enough to scroll (if one is not already long enough).
2. Update the `TextView` component defined in the `activity_detail.xml` file to include a scrollbar.

**Writeup Task - For At Home Completions Only**

Create a document with the following items:
	* A screenshot of the main activity recycler view
  * A screenshot of the details activity
	* A screenshot of the scrollbar added in Task 3

**Lab Completion**

* IN LAB: 
  * Show the working app running on an emulator and the document with the screenshots to the instructor or TA
* AT HOME: 
  * Submit `MainActivity.kt`, `DetailActivity.kt`, `JsonUtils.kt`, `LoadDataTask.kt`, `MyAdapter.kt`, and your answers to the writeup task to the Lab5 drop box folder on D2L 
* Keep a copy of your project work and answers for future reference
