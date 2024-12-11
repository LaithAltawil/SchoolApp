package com.example.schoolapp.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.schoolapp.datasource.local.entity.Student

@Dao
interface StudentDao {

    //insert student
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    //get student by Id
    @Query("SELECT * from student_table WHERE student_id = :id")
    suspend fun get(id: Int): Student?

    //check if student username exist
    @Query("SELECT * from student_table WHERE student_id = :username")
    suspend fun checkStudent(username: String): Student?

    //set student
    @Query("SELECT * from student_table")
    suspend fun setStudent(): Student?

    //update student
    @Update
    suspend fun updateStudent(student: Student)

    //delete student
    @Query("DELETE FROM student_table where student_id = :id")
    suspend fun deleteStudent(id: Int) }