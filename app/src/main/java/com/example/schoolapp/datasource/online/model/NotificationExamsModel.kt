package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NotificationExamsModel(
    @SerializedName("examNotification") val examNotification: Boolean
)