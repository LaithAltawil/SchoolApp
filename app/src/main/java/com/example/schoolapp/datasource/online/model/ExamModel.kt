package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName

data class ExamModel(
    @SerializedName("examId") val examId: Int,
    @SerializedName("examTeacherId") val examTeacherId: Int,
    @SerializedName("examTeacherSubject") val examTeacherSubject: String,
    @SerializedName("examTeacherClass") val examTeacherClass: String,
    @SerializedName("examDate") val examDate: String,
    @SerializedName("examDay") val examDay: String,
    @SerializedName("examMaterial") val examMaterial: String,
    @SerializedName("examNotes") val examNotes: String
)