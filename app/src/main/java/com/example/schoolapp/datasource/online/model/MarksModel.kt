package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class MarksModel(
    @SerializedName("markStudentId") val markStudentId: Int,
    @SerializedName("markTeacherSubject") val markTeacherSubject: String,
    @SerializedName("firstMark") val firstMark: Float,
    @SerializedName("secondMark") val secondMark: Float,
    @SerializedName("thirdMark") val thirdMark: Float,
    @SerializedName("forthMark") val forthMark: Float,
    @SerializedName("totalMark") val totalMark: Float
)
