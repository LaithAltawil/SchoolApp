package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.Data.Subjects

data class MarkspageState(
    val Subjects: List<String> = listOf(
        "Math",
        "Science",
        "History",
        "English"),
    val MarksItems: List<Subjects> = listOf(
        Subjects("Maths",) { },
        Subjects("Science",) { },
        Subjects("English",) { },
        Subjects("History",) {},
        Subjects("Arabic",) {},
        Subjects("Computer Science",) {},
        Subjects("Geography",) {}
    ),
    val isTopBarVisible: Boolean = false,

    val showBottomSheet: List<Boolean> = listOf(false,
        false,
        false,
        false,
        false,
        false,
        false)

)
