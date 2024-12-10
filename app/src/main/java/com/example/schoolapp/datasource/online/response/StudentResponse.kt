package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.StudentModel

data class StudentResponse(
    val error: Boolean,
    val message: String,
    val student: StudentModel?
)
