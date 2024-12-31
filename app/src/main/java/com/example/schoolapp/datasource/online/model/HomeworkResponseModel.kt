package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName

data class HomeworkResponseModel(
    @SerializedName("homeworkId") val homeworkId: Int,
    @SerializedName("studentId") val studentId: Int,
    @SerializedName("filePath") val filePath: String,
    @SerializedName("isComplete") val isComplete: Boolean,
    @SerializedName("submissionDate") val submissionDate: String
)
