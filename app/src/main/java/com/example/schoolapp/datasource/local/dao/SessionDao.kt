package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolapp.datasource.local.entity.Session

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSessions(sessions: List<Session>)

    @Query("SELECT * FROM session_table WHERE session_teacher_class = :classId")
    suspend fun getSessionsByClass(classId: String): List<Session>

    @Query("SELECT * FROM session_table WHERE day = :day")
    suspend fun getSessionsByDay(day: String): List<Session>

    @Query("DELETE FROM session_table")
    suspend fun deleteAllSessions()

    @Query("DELETE FROM session_table WHERE session_teacher_class = :classId")
    suspend fun deleteSessionsByClass(classId: String)
}