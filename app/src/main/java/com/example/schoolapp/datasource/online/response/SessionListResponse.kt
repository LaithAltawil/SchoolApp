package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.SessionModel

data class SessionListResponse(
    val error: Boolean,
    val message: String,
    val sessions: List<SessionModel>
)
