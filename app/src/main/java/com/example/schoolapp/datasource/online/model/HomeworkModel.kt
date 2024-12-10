package com.example.schoolapp.datasource.online.model

import androidx.core.text.util.LocalePreferences.FirstDayOfWeek.Days
import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class HomeworkModel(
    @SerializedName("homeworkId") val homeworkId: String,
    @SerializedName("homeworkTeacherId") val homeworkTeacherId: String,
    @SerializedName("homeworkTeacherClass") val homeworkTeacherClass: String,
    @SerializedName("homeworkTeacherSubject") val homeworkTeacherSubject: String,
    @SerializedName("homeworkDetails") val homeworkDetails: String,
    @SerializedName("homeworkStartDate") val homeworkStartDate: Date,
    @SerializedName("homeworkEndDate") val homeworkEndDate: Date,
    @SerializedName("homeworkStartDay") val homeworkStartDay: DayOfWeek,
    @SerializedName("homeworkEndDay") val homeworkEndDay: DayOfWeek
)
