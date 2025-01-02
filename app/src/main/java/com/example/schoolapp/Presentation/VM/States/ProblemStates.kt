package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.datasource.local.entity.Problem

sealed class ProblemLoadingState {
    object Initial : ProblemLoadingState()
    object Loading : ProblemLoadingState()
    object Success : ProblemLoadingState()
    data class Error(val message: String) : ProblemLoadingState()
}

data class ProblemPageState(
    val currentTab: Int = 0,
    val isLoading: Boolean = false,
    val showTypeDropdown: Boolean = false,
    val showContactDialog: Boolean = false,
    val problemType: String = "",
    val problemDate: String = "",
    val problemNotes: String = "",
    val problems: List<Problem> = emptyList(),
    val scheduledMeetings: List<Problem> = emptyList(),
    val validationError: String? = null
)

sealed class ProblemSubmissionState {
    object Initial : ProblemSubmissionState()
    object Submitting : ProblemSubmissionState()
    object Success : ProblemSubmissionState()
    data class Error(val message: String) : ProblemSubmissionState()
}