package com.example.schoolapp.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.schoolapp.datasource.local.dao.HomeworkDao
import com.example.schoolapp.datasource.local.dao.StudentDao
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Student

@Database(entities = [Student::class, Homework::class], version = 3, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao
    abstract val homeworkDao: HomeworkDao

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
    //= Homework function's                                 =
    //=======================================================
    // Student function's
    suspend fun insert(student: Student) = studentDao.insert(student)
    suspend fun getStudent(id: Int) = studentDao.get(id)
    suspend fun checkStudent(username: String) = studentDao.checkStudent(username)
    suspend fun setStudent() = studentDao.setStudent()
    suspend fun update(student: Student) = studentDao.updateStudent(student)
    suspend fun deleteStudent(id: Int) = studentDao.deleteStudent(id)

    // Homework function's
    suspend fun insertHomework(homework: Homework) = homeworkDao.insert(homework)
    suspend fun getHomework(id: Int) = homeworkDao.get(id)
    suspend fun getAllHomework() = homeworkDao.getListOfHomework()
    suspend fun getHomeworkForTodo() = homeworkDao.getListOfHomeworkBasedOnDate()
    suspend fun getLastHomeworkId() = homeworkDao.getLastHomeworkId()
    suspend fun getLastHomeworkClass() = homeworkDao.getLastHomeworkClass()
    suspend fun deleteHomework(id: Int) = homeworkDao.deleteHomework(id)
    suspend fun deleteAllHomework() = homeworkDao.deleteAllHomework()

}