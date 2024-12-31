package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.HomeworkResponseModel

data class HomeworkResponseListResponse(
    val error: Boolean,
    val message: String,
    val responses: List<HomeworkResponseModel>
)