package com.example.schoolapp.Presentation.VM.States

import java.util.Calendar

data class CounselorsPageState(
    val topAppBarVisible: Boolean = false,
    var selectedDate:String="",
    val SelectedDateAndEnteredID:Boolean=false,
    var Id:String="",
    val openDialog:Boolean=false
)