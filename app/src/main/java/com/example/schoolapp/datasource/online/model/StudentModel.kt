package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class StudentModel(
    @SerializedName("studentId") val studentId: Int,
    @SerializedName("studentUsername") val studentUsername: String,
    @SerializedName("studentPassword") val studentPassword: String,
    @SerializedName("studentClass") val studentClass: String,
    @SerializedName("studentStatus") val studentStatus: String,
    @SerializedName("studentGender") val studentGender: String,
    @SerializedName("studentFirstName") val studentFirstName: String,
    @SerializedName("studentSecondName") val studentSecondName: String,
    @SerializedName("studentThirdName") val studentThirdName: String,
    @SerializedName("studentNationalId") val studentNationalId: Int,
    @SerializedName("studentDateOfRegistration") val studentDateOfRegistration: Date,
    @SerializedName("studentDateOfBirth") val studentDateOfBirth: Date,
    @SerializedName("studentPlaceOfBirth") val studentPlaceOfBirth: String,
    @SerializedName("studentCity") val studentCity: String,
    @SerializedName("studentResidence") val studentResidence: String,
    @SerializedName("studentNationality") val studentNationality: String,
    @SerializedName("studentNotes") val studentNotes: String?,
    @SerializedName("studentProfileImage") val studentProfileImage: String?
)