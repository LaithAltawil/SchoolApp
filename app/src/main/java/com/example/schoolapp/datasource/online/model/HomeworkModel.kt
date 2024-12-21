package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class HomeworkModel(
    @SerializedName("homeworkId") val homeworkId: Int,
    @SerializedName("homeworkTeacherId") val homeworkTeacherId: Int,
    @SerializedName("homeworkTeacherClass") val homeworkTeacherClass: String,
    @SerializedName("homeworkTeacherSubject") val homeworkTeacherSubject: String,
    @SerializedName("homeworkDetails") val homeworkDetails: String,
    @SerializedName("homeworkStartDate") val homeworkStartDate: Date,
    @SerializedName("homeworkEndDate") val homeworkEndDate: Date,
    @SerializedName("homeworkStartDay") val homeworkStartDay: String,
    @SerializedName("homeworkEndDay") val homeworkEndDay: String,
    @SerializedName("homeworkIsCompleted") val homeworkIsCompleted: Boolean,
    @SerializedName("homeworkFilePath") val homeworkFilePath: String
)
