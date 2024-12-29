package com.example.schoolapp.Presentation.VM.States

// In States package
sealed class MarksLoadingState {
    object Initial : MarksLoadingState()
    object Checking : MarksLoadingState()
    object Loading : MarksLoadingState()
    object Completed : MarksLoadingState()
    data class Error(val message: String) : MarksLoadingState()
}