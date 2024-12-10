package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.HomeworkModel

data class HomeworkListResponse(
    val error: Boolean,
    val message: String,
    val homeworks: List<HomeworkModel>
)
