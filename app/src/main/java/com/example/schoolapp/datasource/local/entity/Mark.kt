package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val EMPTY_MARK = -1f

@Entity(
    tableName = "marks_table",
    primaryKeys = ["mark_student_id", "mark_teacher_subject"] // Composite primary key
)
data class Mark(
    @ColumnInfo(name = "mark_student_id")
    var markStudentId: Int,

    @ColumnInfo(name = "mark_teacher_subject")
    var markTeacherSubject: String,

    @ColumnInfo(name = "first_mark")
    var firstMark: Float,

    @ColumnInfo(name = "second_mark")
    var secondMark: Float,

    @ColumnInfo(name = "third_mark")
    var thirdMark: Float,

    @ColumnInfo(name = "forth_mark")
    var forthMark: Float,

    @ColumnInfo(name = "total_mark")
    var totalMark: Float
) {
    companion object {
        fun empty(studentId: Int, subject: String) = Mark(
            markStudentId = studentId,
            markTeacherSubject = subject,
            firstMark = 0f,
            secondMark = 0f,
            thirdMark = 0f,
            forthMark = 0f,
            totalMark = 0f
        )
    }
}