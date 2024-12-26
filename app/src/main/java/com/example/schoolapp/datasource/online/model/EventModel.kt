package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName

data class EventModel(
    @SerializedName("eventId") val eventId: Int,
    @SerializedName("eventName") val eventName: String,
    @SerializedName("eventDetails") val eventDetails: String,
    @SerializedName("eventStartDate") val eventStartDate: String,
    @SerializedName("eventStartDay") val eventStartDay: String,
    @SerializedName("eventEndDate") val eventEndDate: String,
    @SerializedName("eventEndDay") val eventEndDay: String
)