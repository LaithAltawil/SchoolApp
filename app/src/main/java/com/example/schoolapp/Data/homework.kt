package com.example.schoolapp.Data

import androidx.compose.ui.graphics.painter.Painter

//=======================================================
//Declaring data classes                               =
//=======================================================

//*todo @LT #simple || class name should start with upper case latter
//*todo @MAS #meduim || recreate data class to match the database
data class homework(
    val title: String,
    val description: String,
    val dueDate: String,
    val isCompleted: Boolean
)

//todo @LT #simple || variable name should start with small case latter
//*todo @MAS #meduim || recreate data class to match the database
data class Subjects(
    val name: String,
    val ImagePath: Painter? = null,
    val onClick: () -> Unit
)

//todo @LT #qustion[not answered] || why this class exist?
//*todo @MAS #meduim || recreate data class to match the database
data class CalenderDays(
    val day: String,
    val event: String,
    val onClick: () -> Unit
)

//todo @LT #qustion[not answered] || is this for the app's main activity or for another thing?
data class MainMenuItem(
    val title: String,
    val icon: Painter,
    val onClick: () -> Unit
)