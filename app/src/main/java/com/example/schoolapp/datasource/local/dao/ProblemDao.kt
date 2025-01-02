package com.example.schoolapp.datasource.local.dao

import androidx.room.*
import com.example.schoolapp.datasource.local.entity.Problem

@Dao
interface ProblemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblem(problem: Problem)

    @Query("SELECT * FROM problem_table WHERE student_id_problem = :studentId ORDER BY problem_date DESC")
    suspend fun getStudentProblems(studentId: Int): List<Problem>

    @Query("SELECT * FROM problem_table WHERE student_id_problem = :studentId AND problem_status = 0")
    suspend fun getActiveProblems(studentId: Int): List<Problem>

    @Query("SELECT * FROM problem_table WHERE student_id_problem = :studentId AND problem_status = 1")
    suspend fun getResolvedProblems(studentId: Int): List<Problem>

    @Query("DELETE FROM problem_table WHERE student_id_problem = :studentId")
    suspend fun deleteStudentProblems(studentId: Int)

    @Update
    suspend fun updateProblem(problem: Problem)

    @Query("SELECT * FROM problem_table WHERE student_id_problem = :studentId AND problem_discussion_date IS NOT NULL")
    suspend fun getScheduledProblems(studentId: Int): List<Problem>
}