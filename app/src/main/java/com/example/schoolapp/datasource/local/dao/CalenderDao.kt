package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Homework
import kotlinx.coroutines.flow.StateFlow

@Dao
interface CalenderDao {
    //===========================================================================================
    //calender event dao's                                                                      =
    //===========================================================================================
    //insert event
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalenderEvent(calenderEvent: CalenderEvent)

    //get event by Id
    @Query("SELECT * from calender_table where event_id = :id")
    suspend fun getCalenderEvent(id: Int): CalenderEvent?

    //get all events
    @Query("SELECT * from calender_table")
    suspend fun getCalenderEvents(): List<CalenderEvent>

    //delete all events
    @Query("DELETE FROM calender_table")
    suspend fun deleteAllCalenderEvents()

    //===========================================================================================
    //event dao's                                                                               =
    //===========================================================================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM event_table")
    suspend fun getAllEvents(): List<Event>

    @Query("SELECT * FROM event_table WHERE event_id = :id")
    suspend fun getEvent(id: Int): Event?

    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvents()

}