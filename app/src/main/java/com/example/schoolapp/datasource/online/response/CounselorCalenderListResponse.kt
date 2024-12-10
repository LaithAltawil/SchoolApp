package com.example.schoolapp.datasource.online.response

import com.example.schoolapp.datasource.online.model.CalenderSemesterEventModel
import com.example.schoolapp.datasource.online.model.CounselorCalenderEventModel

data class CounselorCalenderListResponse(
    val error: Boolean,
    val message: String,
    val counselorCalenderEvents: List<CounselorCalenderEventModel>
)
