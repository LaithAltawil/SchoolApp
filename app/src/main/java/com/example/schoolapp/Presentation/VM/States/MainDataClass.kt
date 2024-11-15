package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.Homework

//Data source
sealed class MainDataClass{

    //todo @LT #simple|| "Id" [13:13] must start with small latter
    //todo @LT #simple|| add usage
    data class CounselorsPageState1(
        val isTopAppBarVisible: Boolean = false,
        var selectedDate: String = "",
        var Id: String = "",
        val openDialog: Boolean = false
    ) :MainDataClass()

    //todo @LT #simple|| "Subjects" [22:21] must start with small latter
    //todo @LT #simple|| add usage
    data class ExamPageState1(
        val showBottomSheet: Boolean = false,
        val Subjects: List<String> = listOf(
            "Math",
            "Science",
            "History",
            "English",
            "Spanish",
            "French",
            "Arabic",
            "Computer Science"
        ),
        val isTopBarVisible: Boolean = false
        ) :MainDataClass()

    //todo @LT #simple|| add usage
    data class HomeworkPageState1(
        val homework: List<Homework> = emptyList(),
        val isTopBarVisible: Boolean = false,
    ) : MainDataClass()

    //todo @LT #simple|| "MarkspageState1" [45:21] must start with capital latter
    //todo @LT #simple|| "Subjects" [46:13] must start with small latter
    //todo @LT #simple|| "MarksItems" [52:13] must start with small latter
    //todo @LT #simple|| add usage
    data class MarkspageState1(
        val Subjects: List<String> = listOf(
            "Math",
            "Science",
            "History",
            "English"
        ),
        val MarksItems: List<Subjects> = listOf(
            Subjects("Maths") { },
            Subjects("Science") { },
            Subjects("English") { },
            Subjects("History") {},
            Subjects("Arabic") {},
            Subjects("Computer Science") {},
            Subjects("Geography") {}
        ),
        val isTopBarVisible: Boolean = false,
        val showBottomSheet: List<Boolean> = listOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )
    ) :MainDataClass()

    //todo @LT #simple|| add usage
    data class SettingsPageState1(
        val isTopBarVisible: Boolean = false
    ) :MainDataClass()

    //todo @LT #simple|| "Subjects" [82:13] must start with small latter
    //todo @LT #simple|| add usage
    data class ResourcesPageState(
        val isTopAppBarVisible: Boolean = false,
        val Subjects: List<Subjects> = listOf(
            Subjects("Maths") { },
            Subjects("Science") { },
            Subjects("English") { },
            Subjects("History") {},
            Subjects("Arabic") {},
            Subjects("Computer Science") {},
            Subjects("Geography") {}
        ),
        val showBottomSheet: List<Boolean> = listOf(false, false, false, false, false, false, false)
    ) : MainDataClass()

    //todo @LT #simple|| add usage
    data class ClassesPageState(
        val isTopAppBarVisible: Boolean = false,
    ) : MainDataClass()

    //todo @LT #simple|| add usage
    data class CalenderPage(
        val isTopAppBarVisible: Boolean = false,
        ) : MainDataClass()
}