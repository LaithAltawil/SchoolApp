package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Student_table")
data class Student(
    @PrimaryKey()
    @ColumnInfo(name = "student_id")
    var studentId: Int,

    @ColumnInfo(name = "student_username")
    var studentUsername: String,

    @ColumnInfo(name = "student_password")
    var studentPassword: String,

    @ColumnInfo(name = "student_class")
    var studentClass: String,

    @ColumnInfo(name = "student_status")
    var studentStatus: String,

    @ColumnInfo(name = "student_gender")
    var studentGender: String,

    @ColumnInfo(name = "student_first_name")
    var studentFirstName: String,

    @ColumnInfo(name = "student_second_name")
    var studentSecondName: String,

    @ColumnInfo(name = "student_third_name")
    var studentThirdName: String,

    @ColumnInfo(name = "student_national_id")
    var studentNationalId: Int,

    @ColumnInfo(name = "student_date_of_registration")
    var studentDateOfRegistration: String,

    @ColumnInfo(name = "student_date_of_birth")
    var studentDateOfBirth: String,

    @ColumnInfo(name = "student_place_of_birth")
    var studentPlaceOfBirth: String,

    @ColumnInfo(name = "student_city")
    var studentCity: String,

    @ColumnInfo(name = "student_residence")
    var studentResidence: String,

    @ColumnInfo(name = "student_nationality")
    var studentNationality: String,

    @ColumnInfo(name = "student_notes")
    var studentNotes: String?,

    @ColumnInfo(name = "student_profile_image")
    var studentProfileImage: String?
)