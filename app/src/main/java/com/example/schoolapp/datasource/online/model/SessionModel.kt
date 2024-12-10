package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class SessionModel(
    @SerializedName("sessionTeacherClass") val sessionTeacherClass: String,
    @SerializedName("day") val day: DayOfWeek,
    @SerializedName("session") val session: String,
    @SerializedName("sessionTeacherSubject") val sessionTeacherSubject: String,
    @SerializedName("sessionTeacherId") val sessionTeacherId: Int
)
