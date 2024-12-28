package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_table")
data class Session(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "session_id")
    var sessionId: Int,

    @ColumnInfo(name = "session_teacher_class")
    var sessionTeacherClass: String,

    @ColumnInfo(name = "day")
    var day: String,

    @ColumnInfo(name = "session")
    var session: String,

    @ColumnInfo(name = "session_teacher_subject")
    var sessionTeacherSubject: String,

    @ColumnInfo(name = "session_teacher_id")
    var sessionTeacherId: Int
)