package com.example.schoolapp.Presentation.VM.States

// Create in States/CalenderLoadingState.kt
sealed class CalenderLoadingState {
    object Initial : CalenderLoadingState()
    object CheckingCalender : CalenderLoadingState()
    object FetchingCalender : CalenderLoadingState()
    object CheckingNewCalender : CalenderLoadingState()
    object Completed : CalenderLoadingState()
}