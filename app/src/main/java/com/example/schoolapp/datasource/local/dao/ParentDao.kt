package com.example.schoolapp.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Student

@Dao
interface ParentDao {
    //insert parent
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(parent: Parent)

    //set parent
    @Query("SELECT * from parent_table")
    suspend fun setParent(): Parent?

    //delete parent
    @Query("DELETE FROM parent_table")
    suspend fun deleteParent()
}