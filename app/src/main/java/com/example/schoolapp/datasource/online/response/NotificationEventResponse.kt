package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.NotificationEventModel

data class NotificationEventResponse(
    val error: Boolean,
    val message: String,
    val notificationEvent: NotificationEventModel
)
