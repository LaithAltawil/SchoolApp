package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.NotificationProblemsModel

data class NotificationProblemsListResponse(
    val error: Boolean,
    val message: String,
    val notificationProblems: List<NotificationProblemsModel>?
)
