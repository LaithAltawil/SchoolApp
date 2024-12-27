// ExamDao.kt
package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolapp.datasource.local.entity.Exam

@Dao
interface ExamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: Exam)

    @Query("SELECT * FROM exam_table")
    suspend fun getExams(): List<Exam>

    @Query("SELECT * FROM exam_table where exam_date >= DATE('now')")
    suspend fun getNewExams(): List<Exam>

    @Query("SELECT * FROM exam_table WHERE exam_id = :id")
    suspend fun getExam(id: Int): Exam?

    @Query("SELECT * FROM exam_table WHERE exam_teacher_class = :studentClass")
    suspend fun getExamsByClass(studentClass: String): List<Exam>

    @Query("DELETE FROM exam_table")
    suspend fun deleteAllExams()
}