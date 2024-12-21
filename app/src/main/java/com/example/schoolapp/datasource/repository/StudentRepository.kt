package com.example.schoolapp.datasource.repository

import android.util.Log
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.api.StudentDatabaseApi
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.StudentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import retrofit2.Response

class StudentRepository(
    private val studentDatabase: StudentDatabase,
    private val studentApi: StudentDatabaseApi = StudentDatabaseApi()
) {
    //==========================================
    //ROOM                                     =
    //==========================================
    //student Dao fun's
    //insert student from the online database
    suspend fun insertStudent(student: Student) {
        withContext(Dispatchers.IO) {
            studentDatabase.insert(student)
        }
    }

    //get student with id
    suspend fun getStudent(id: Int): Student? {
        val student = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.getStudent(id)
        }
        return student
    }

    //get student with username
    suspend fun checkStudent(username: String): Student? {
        val student = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.checkStudent(username)
        }
        return student
    }

    //get student data
    suspend fun setStudent(): Student? {
        val student = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.setStudent()
        }
        return student
    }

    //update student
    suspend fun updateStudent(student: Student) {
        withContext(Dispatchers.IO) {
            studentDatabase.update(student)
        }
    }

    //delete student
    suspend fun deleteStudent(id: Int) {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteStudent(id)
        }
    }

    //homework dao's fun
    //insert homework from the online database
    suspend fun insertHomework(homework: Homework) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertHomework(homework)
        }
    }

    //get homework with id
    suspend fun getHomework(id: Int): Homework? {
        val homework = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.getHomework(id)
        }
        return homework
    }

    //get all homework list
    suspend fun getAllHomework(): List<Homework> {
        val homeworkList = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.getAllHomework()
        }
        return homeworkList
    }

    //get homework starting from today
    suspend fun getHomeworkForTodo(): List<Homework> {
        val homeworkList = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.getHomeworkForTodo()
        }
        return homeworkList
    }

    suspend fun getLastHomeworkId(): Int {
        val lastId = withContext(Dispatchers.IO) {
            studentDatabase.getLastHomeworkId()
        }
        return lastId
    }

    suspend fun getLastHomeworkClass(): String {
        val lastClass = withContext(Dispatchers.IO) {
            studentDatabase.getLastHomeworkClass()
        }
        return lastClass
    }

    //delete homework
    suspend fun deleteHomework(id: Int) {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteHomework(id)
        }
    }

    //delete all homework
    suspend fun deleteAllHomework() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllHomework()
        }
    }

    //==========================================
    //API                                      =
    //==========================================
    //student sign-in API
    suspend fun getStudentFromApi(studentUsername: String): Response<StudentResponse> {
        val studentResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.studentSignIn(studentUsername)
        }
        return studentResponse
    }

    //Homework API
    suspend fun getHomeworkFromApi(studentClass: String): Response<HomeworkListResponse> {
        val homeworkResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.homeworks(studentClass)
        }
        return homeworkResponse
    }

    //Homework API (by Id)
    suspend fun getHomeworkByIdFromApi(
        studentClass: String,
        homeworkId: Int
    ): Response<HomeworkListResponse> {
        val homeworkResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.homeworkById(studentClass, homeworkId)
        }
        return homeworkResponse
    }

    //update homework
    suspend fun updateHomework(isCompleted: Boolean,filePath: String, id: Int) {
        withContext(Dispatchers.IO) {
            studentApi.updateHomework(isCompleted,filePath,id)
        }
    }
}