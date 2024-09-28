package com.example.schoolapp.Presentation.VM.States

data class CounselorsPageState(
    val topAppBarVisible: Boolean = false,
    var Date: String = "",
    val SelectedDateAndEnteredID:Boolean=false,
    var Id:String="",
)