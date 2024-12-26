package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calender_table")
data class CalenderEvent(
    @PrimaryKey()
    @ColumnInfo(name = "event_id")
    var eventId: Int,

    @ColumnInfo(name = "event_description")
    var eventDescription: String,

    @ColumnInfo(name = "event_start_date")
    var eventStartDate: String,

    @ColumnInfo(name = "event_end_date")
    var eventEndDate: String,

    @ColumnInfo(name = "event_start_day")
    var eventStartDay: String
)