package com.example.schoolapp.Presentation.VM

data class ExamPageState(
    val showBottomSheet: Boolean = false,
    val Subjects : List<String> = listOf(
        "Math",
        "Science",
        "History",
        "English",
        "Spanish",
        "French",
        "Arabic",
        "Computer Science",
    )

)
