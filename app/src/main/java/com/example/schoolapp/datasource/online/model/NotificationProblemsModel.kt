package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NotificationProblemsModel(
    @SerializedName("problemType") val problemType: String,
    @SerializedName("problemDate") val problemDate: Date,
    @SerializedName("problemStatus") val problemStatus: String,
    @SerializedName("problemDiscussionDate") val problemDiscussionDate: Date?,
    @SerializedName("problemDiscussionSession") val problemDiscussionSession: String?
)