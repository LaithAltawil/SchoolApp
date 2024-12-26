package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.ParentModel

data class ParentResponse(
    val error: Boolean,
    val message: String,
    val parent: ParentModel
)
