package com.example.schoolapp.datasource.online.model

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.util.Date

data class CounselorCalenderEventModel(
    @SerializedName("problemNotes") val problemNotes: String,
    @SerializedName("problemDate") val problemDate: Date,
    @SerializedName("problemStatus") val problemStatus: Boolean,
    @SerializedName("problemDiscussionDate") val problemDiscussionDate: Date?,
    @SerializedName("problemDiscussionSession") val problemDiscussionSession: String?
)
