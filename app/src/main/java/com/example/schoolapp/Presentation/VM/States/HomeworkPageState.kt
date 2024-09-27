package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.Data.homework

data class HomeworkPageState(
    val homework: List<homework> = emptyList(),
    val isLoading: Boolean = false,
)
