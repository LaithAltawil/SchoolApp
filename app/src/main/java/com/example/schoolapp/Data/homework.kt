package com.example.schoolapp.Data

import androidx.compose.ui.graphics.painter.Painter

data class homework(
    val title: String,
    val description: String,
    val dueDate: String,
    val isCompleted: Boolean
)

data class Subjects(
    val name: String,
    val ImagePath: Painter? =null,
    val onClick: () -> Unit
)
data class CalenderDays(
    val day: String,
    val event: String,
    val onClick: () -> Unit
)
data class MainMenuItem(
    val title: String,
    val icon: Painter,
    val onClick: () -> Unit
)
