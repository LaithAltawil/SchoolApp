package com.example.schoolapp.Presentation.VM.States

data class MarkspageState(
    val showBottomSheet: Boolean = false,
    val Subjects : List<String> = listOf(
        "Math",
        "Science",
        "History",
        "English"),
    val isTopBarVisible: Boolean =false

)
