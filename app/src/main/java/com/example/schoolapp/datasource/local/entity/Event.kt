// Event.kt
package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey
    @ColumnInfo(name = "event_id")
    var eventId: Int,

    @ColumnInfo(name = "event_name")
    var eventName: String,

    @ColumnInfo(name = "event_details")
    var eventDetails: String,

    @ColumnInfo(name = "event_start_date")
    var eventStartDate: String,

    @ColumnInfo(name = "event_start_day")
    var eventStartDay: String,

    @ColumnInfo(name = "event_end_date")
    var eventEndDate: String,

    @ColumnInfo(name = "event_end_day")
    var eventEndDay: String
)