package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.Homework

sealed class MainDataClass{

    data class CounselorsPageState1(
        val isTopAppBarVisible: Boolean = false,
        var selectedDate: String ="",
        var Id:String="",
        val openDialog:Boolean=false
    ):MainDataClass()

    data class ExamPageState1(
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
        ),
        val isTopBarVisible: Boolean = false,

    ):MainDataClass()

    data class HomeworkPageState1(
        val homework: List<Homework> = emptyList(),
        val isTopBarVisible: Boolean = false,
    ):MainDataClass()

    data class MarkspageState1(
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

    data class SettingsPageState1(
        val isTopBarVisible: Boolean = false
    ):MainDataClass()

    data class ResourcesPageState(
        val isTopAppBarVisible: Boolean = false,
        val Subjects: List<Subjects> = listOf(
            Subjects("Maths",) { },
            Subjects("Science",) { },
            Subjects("English",) { },
            Subjects("History",) {},
            Subjects("Arabic",) {},
            Subjects("Computer Science",) {},
            Subjects("Geography",) {}
        ),
        val showBottomSheet: List<Boolean> = listOf(false,false,false,false,false,false,false)

    ):MainDataClass()

    data class ClassesPageState(
        val isTopAppBarVisible: Boolean = false,
    ):MainDataClass()

    data class CalenderPage(
        val isTopAppBarVisible: Boolean = false,

    ):MainDataClass()











}