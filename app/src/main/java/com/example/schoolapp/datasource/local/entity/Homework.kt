package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homework_table")
data class Homework(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "homework_id")
    val homeworkId: Int,

    @ColumnInfo(name = "homework_teacher_id")
    val homeworkTeacherId: Int,

    @ColumnInfo(name = "homework_teacher_class")
    val homeworkTeacherClass: String,

    @ColumnInfo(name = "homework_teacher_subject")
    val homeworkTeacherSubject: String,

    @ColumnInfo(name = "homework_details")
    val homeworkDetails: String,

    @ColumnInfo(name = "homework_start_date")
    val homeworkStartDate: String,

    @ColumnInfo(name = "homework_end_date")
    val homeworkEndDate: String,

    @ColumnInfo(name = "homework_start_day")
    val homeworkStartDay: String,

    @ColumnInfo(name = "homework_end_day")
    val homeworkEndDay: String
)