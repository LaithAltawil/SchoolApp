package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName

data class TeacherModel(
    @SerializedName("teacherFirstName") val teacherFirstName: String,
    @SerializedName("teacherSecondName") val teacherSecondName: String,
    @SerializedName("teacherThirdName") val teacherThirdName: String
)