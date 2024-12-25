package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NotificationEventModel(
    @SerializedName("eventNotification") val eventNotification: Boolean
)