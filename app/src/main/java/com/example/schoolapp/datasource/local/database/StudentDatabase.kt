package com.example.schoolapp.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.schoolapp.datasource.local.dao.StudentDao
import com.example.schoolapp.datasource.local.entity.Student

@Database(entities = [Student::class], version = 0, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao

    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    //=======================================================
    //= Dao functions                                       =
    //= Student function's                                  =
    //=======================================================
    suspend fun insert(student: Student) = studentDao.insert(student)
    suspend fun getStudent(id: Int) = studentDao.get(id)
    suspend fun checkStudent(username: String) = studentDao.checkStudent(username)
    suspend fun setStudent() = studentDao.setStudent()
    suspend fun update(student: Student) = studentDao.updateStudent(student)
    suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)
    }