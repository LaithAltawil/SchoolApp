package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.EventModel

data class EventListResponse(
    val error: Boolean,
    val message: String,
    val calenderEvents: List<EventModel>
)