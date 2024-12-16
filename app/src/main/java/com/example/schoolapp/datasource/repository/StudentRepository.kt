package com.example.schoolapp.datasource.repository

import android.util.Log
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.api.StudentDatabaseApi
import com.example.schoolapp.datasource.online.response.StudentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class StudentRepository(
    private val studentDatabase: StudentDatabase,
    private val studentApi: StudentDatabaseApi = StudentDatabaseApi()
) {
    //==========================================
    //ROOM                                     =
    //==========================================
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

    //==========================================
    //API                                      =
    //==========================================
    //student sign-in API
    suspend fun getStudentFromApi(studentUsername: String): Response<StudentResponse> {
        val studentResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.studentSignIn("student_sign_in",studentUsername);
        }
        Log.d("studentResponse", studentResponse.toString())
        return studentResponse
    }
}