package com.example.schoolapp.Data

import androidx.compose.ui.graphics.painter.Painter

//=======================================================
//Declaring data classes                               =
//=======================================================

//*todo @LT #simple || class name should start with upper case latter
//done
//*todo @MAS #meduim || recreate data class to match the database
data class Homework(
    val title: String,
    val description: String,
    val dueDate: String,
    val isCompleted: Boolean
)

//todo @LT #simple || variable name should start with small case latter
//done anything left please tell me or leave a todo
//*todo @MAS #meduim || recreate data class to match the database
data class Subjects(
    val name: String,
    val imagePath: Painter? = null,
    val onClick: () -> Unit
)

//todo @LT #qustion[not answered] || why this class exist?
//to add dates and events from the database to the Calender page
//*todo @MAS #meduim || recreate data class to match the database
data class CalenderDays(
    val day: String,
    val event: String,
    val onClick: () -> Unit
)

//todo @LT #qustion[not answered] || is this for the app's main activity or for another thing?
//this is the main menu item data class, where you use it to give a name and an icon to each item in the main menu
data class MainMenuItem(
    val title: String,
    val icon: Painter,
    val onClick: () -> Unit
)
data class Class(
    val subjectName: String,
    val teacher: String,
    val time: String
)
data class Exam(
    val subject: String,
    val date: String,
    val time: String,
    val location: String
)