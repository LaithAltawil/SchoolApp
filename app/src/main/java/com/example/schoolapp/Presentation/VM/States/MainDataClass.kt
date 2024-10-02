package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.homework

sealed class MainDataClass{

    data class CounselorsPageState(
        val topAppBarVisible: Boolean = false,
        var selectedDate:String="",
        val SelectedDateAndEnteredID:Boolean=false,
        var Id:String="",
        val openDialog:Boolean=false
    ):MainDataClass()

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

    ):MainDataClass()

    data class HomeworkPageState1(
        val homework: List<homework> = emptyList(),
        val isLoading: Boolean = false,
    ):MainDataClass()

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

    ):MainDataClass()

    data class SettingsPageState(
        val isTopBarVisible: Boolean = false
    ):MainDataClass()





}