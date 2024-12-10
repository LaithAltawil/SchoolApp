package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.ExamModel

data class ExamListResponse(
    val error: Boolean,
    val message: String,
    val exams: List<ExamModel>
)
