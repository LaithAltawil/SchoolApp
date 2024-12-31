package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName

data class HomeworkModel(
    @SerializedName("homeworkId") val homeworkId: Int,
    @SerializedName("homeworkTeacherId") val homeworkTeacherId: Int,
    @SerializedName("homeworkTeacherClass") val homeworkTeacherClass: String,
    @SerializedName("homeworkTeacherSubject") val homeworkTeacherSubject: String,
    @SerializedName("homeworkDetails") val homeworkDetails: String,
    @SerializedName("homeworkStartDate") val homeworkStartDate: String,
    @SerializedName("homeworkEndDate") val homeworkEndDate: String,
    @SerializedName("homeworkStartDay") val homeworkStartDay: String,
    @SerializedName("homeworkEndDay") val homeworkEndDay: String
)