package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolapp.datasource.local.entity.Homework
import kotlinx.coroutines.flow.StateFlow

@Dao
interface HomeworkDao {
    //insert homework
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homework: Homework)

    //get homework by Id
    @Query("SELECT * from homework_table WHERE homework_id = :id")
    suspend fun get(id: Int): Homework?

    //get all homework
    @Query("SELECT * from homework_table")
    suspend fun getListOfHomework(): List<Homework>

    //get list of homework based on date
    @Query("SELECT * from homework_table where homework_start_date >= DATE('now')")
    suspend fun getListOfHomeworkBasedOnDate(): List<Homework>

    //get the last homework Id
    @Query("SELECT homework_id from homework_table ORDER BY homework_id DESC LIMIT 1")
    suspend fun getLastHomeworkId(): Int

    //delete homework
    @Query("DELETE FROM homework_table where homework_id = :id")
    suspend fun deleteHomework(id: Int)

    //delete all homework
    @Query("DELETE FROM homework_table")
    suspend fun deleteAllHomework()

}