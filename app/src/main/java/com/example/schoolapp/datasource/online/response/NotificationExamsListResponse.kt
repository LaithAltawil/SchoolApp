package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.NotificationExamsModel

data class NotificationExamsListResponse(
    val error: Boolean,
    val message: String,
    val notificationExams: List<NotificationExamsModel>
)
