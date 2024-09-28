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
