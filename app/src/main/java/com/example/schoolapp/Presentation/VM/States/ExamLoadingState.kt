package com.example.schoolapp.Presentation.VM.States

sealed class ExamLoadingState {
    object Initial : ExamLoadingState()
    object Loading : ExamLoadingState()
    object Checking : ExamLoadingState()
    object CheckingNew : ExamLoadingState()
    object Fetching : ExamLoadingState()
    object Completed : ExamLoadingState()
    data class Error(val message: String) : ExamLoadingState()
}