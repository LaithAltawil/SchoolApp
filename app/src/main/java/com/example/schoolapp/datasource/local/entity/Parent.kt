package com.example.schoolapp.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parent_table")
data class Parent(
    @PrimaryKey
    @ColumnInfo(name = "parent_id")
    var parentId: Int,

    @ColumnInfo(name = "parent_phone_number")
    var parentPhoneNumber: Long,

    @ColumnInfo(name = "parent_job")
    var parentJob: String,

    @ColumnInfo(name = "parent_nationality")
    var parentNationality: String,

    @ColumnInfo(name = "parent_address")
    var parentAddress: String
)