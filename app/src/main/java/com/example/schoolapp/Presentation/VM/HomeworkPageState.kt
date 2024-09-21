package com.example.schoolapp.Presentation.VM

import com.example.schoolapp.Data.homework

data class HomeworkPageState(
    val homework: List<homework> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isExpanded: Boolean = false
)
