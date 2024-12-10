package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.CalenderSemesterEventModel

data class CalenderSemesterListResponse(
    val error: Boolean,
    val message: String,
    val calenderSemesterEvents: List<CalenderSemesterEventModel>
)
