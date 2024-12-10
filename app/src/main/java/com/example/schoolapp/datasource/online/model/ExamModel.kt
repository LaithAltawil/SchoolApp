package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class ExamModel(
    @SerializedName("examId") val examId: Int,
    @SerializedName("examTeacherId") val examTeacherId: Int,
    @SerializedName("examTeacherSubject") val examTeacherSubject: String,
    @SerializedName("examTeacherClass") val examTeacherClass: String,
    @SerializedName("examDate") val examDate: Date,
    @SerializedName("examDay") val examDay: DayOfWeek,
    @SerializedName("examMaterial") val examMaterial: String,
    @SerializedName("examNotes") val examNotes: String?
)
