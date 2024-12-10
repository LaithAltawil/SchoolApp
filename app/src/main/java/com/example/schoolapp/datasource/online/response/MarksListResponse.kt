package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.ExamModel
import com.example.schoolapp.datasource.online.model.MarksModel

data class MarksListResponse(
    val error: Boolean,
    val message: String,
    val marks: List<MarksModel>
)
