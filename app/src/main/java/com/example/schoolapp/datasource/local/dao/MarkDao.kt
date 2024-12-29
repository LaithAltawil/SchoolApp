package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolapp.datasource.local.entity.Mark

@Dao
interface MarkDao {
    // Insert mark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mark: Mark)
    
    // Get all marks for a student
    @Query("SELECT * FROM marks_table WHERE mark_student_id = :studentId")
    suspend fun getMarks(studentId: Int): List<Mark>
    
    // Delete all marks for a student
    @Query("DELETE FROM marks_table WHERE mark_student_id = :studentId")
    suspend fun deleteMarks(studentId: Int)
    
    // Delete all marks
    @Query("DELETE FROM marks_table")
    suspend fun deleteAllMarks()
}