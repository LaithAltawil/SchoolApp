package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ParentModel(
    @SerializedName("parentPhoneNumber") val parentPhoneNumber: Long,
    @SerializedName("parentJob") val parentJob: String,
    @SerializedName("parentNationality") val parentNationality: String,
    @SerializedName("parentAddress") val parentAddress: String
)