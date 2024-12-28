package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.TeacherModel

data class TeacherResponse(
    val error: Boolean,
    val message: String,
    val teacher: TeacherModel
)