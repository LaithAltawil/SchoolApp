package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CalenderSemesterEventModel(
    @SerializedName("eventId") val eventId: Int,
    @SerializedName("eventDescription") val eventDescription: String,
    @SerializedName("eventStartDate") val eventStartDate: String,
    @SerializedName("eventEndDate") val eventEndDate: String,
    @SerializedName("eventStartDay") val eventStartDay: String
)
