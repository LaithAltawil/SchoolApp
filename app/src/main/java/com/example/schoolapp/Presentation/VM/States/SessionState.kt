package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.datasource.online.model.SessionModel

data class SessionState(
    val sessions: List<SessionModel> = emptyList(), // Changed from Map to List
    val currentDay: String = "",
    val availableDays: List<String> = listOf("sunday", "monday", "tuesday", "wednesday", "thursday"),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class SessionLoadingState {
    object Initial : SessionLoadingState()
    object LoadingData : SessionLoadingState()
    object Completed : SessionLoadingState()
    data class Error(val message: String) : SessionLoadingState()
}