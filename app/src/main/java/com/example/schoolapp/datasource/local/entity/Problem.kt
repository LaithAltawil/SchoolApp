package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problem_table")
data class Problem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "problem_id")
    val problemId: Int = 0,

    @ColumnInfo(name = "student_id_problem")
    val studentIdProblem: Int,

    @ColumnInfo(name = "problem_type")
    val problemType: String,

    @ColumnInfo(name = "problem_notes")
    val problemNotes: String,

    @ColumnInfo(name = "problem_date")
    val problemDate: String,

    @ColumnInfo(name = "problem_status")
    val problemStatus: Int = 0,

    @ColumnInfo(name = "problem_discussion_date")
    val problemDiscussionDate: String? = null,

    @ColumnInfo(name = "problem_discussion_session")
    val problemDiscussionSession: String? = null
)