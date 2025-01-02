// ProblemModel.kt
package com.example.schoolapp.datasource.online.response

import com.google.gson.annotations.SerializedName

data class ProblemModel(
    @SerializedName("problemId") val problemId: Int,
    @SerializedName("studentIdProblem") val studentIdProblem: Int,
    @SerializedName("problemType") val problemType: String,
    @SerializedName("problemNotes") val problemNotes: String,
    @SerializedName("problemDate") val problemDate: String,
    @SerializedName("problemStatus") val problemStatus: Boolean,
    @SerializedName("problemDiscussionDate") val problemDiscussionDate: String?,
    @SerializedName("problemDiscussionSession") val problemDiscussionSession: String?
)

data class ProblemResponse(
    val error: Boolean,
    val message: String,
    val problem: ProblemModel?
)

data class ProblemListResponse(
    val error: Boolean,
    val message: String,
    val problems: List<ProblemModel>
)