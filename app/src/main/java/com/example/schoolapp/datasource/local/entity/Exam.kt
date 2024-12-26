// Exam.kt
package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exam_table")
data class Exam(
    @PrimaryKey
    @ColumnInfo(name = "exam_id")
    var examId: Int,

    @ColumnInfo(name = "exam_teacher_id")
    var examTeacherId: Int,

    @ColumnInfo(name = "exam_teacher_subject")
    var examTeacherSubject: String,

    @ColumnInfo(name = "exam_teacher_class")
    var examTeacherClass: String,

    @ColumnInfo(name = "exam_date")
    var examDate: String,

    @ColumnInfo(name = "exam_day")
    var examDay: String,

    @ColumnInfo(name = "exam_material")
    var examMaterial: String,

    @ColumnInfo(name = "exam_notes")
    var examNotes: String
)