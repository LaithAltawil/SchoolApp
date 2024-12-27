package com.example.schoolapp.datasource.repository

import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Exam
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.api.StudentDatabaseApi
import com.example.schoolapp.datasource.online.response.CalenderSemesterListResponse
import com.example.schoolapp.datasource.online.response.EventListResponse
import com.example.schoolapp.datasource.online.response.ExamListResponse
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.ParentResponse
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

    //===============================================
    //homework dao's fun                            =
    //===============================================
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

    //===============================================
    //parent dao's fun                              =
    //===============================================
    //insert parent
    suspend fun insertParent(parent: Parent) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertParent(parent)
        }
    }

    //set parent
    suspend fun setParent(): Parent? {
        val parent = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.setParent()
        }
        return parent
    }

    //delete parent
    suspend fun deleteParent() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteParent()
        }
    }

    //===============================================
    //calender                                      =
    //===============================================
    // Calendar Semester functions
    suspend fun insertCalenderEvent(calenderEvent: CalenderEvent) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertCalenderEvent(calenderEvent)
        }
    }

    suspend fun getCalenderEvent(id: Int): CalenderEvent? {
        return withContext(Dispatchers.IO) {
            studentDatabase.getCalenderEvent(id)
        }
    }

    suspend fun getCalenderEvents(): List<CalenderEvent> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getCalenderEvents()
        }
    }

    suspend fun deleteAllCalenderEvents() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllCalenderEvents()
        }
    }

    // Event functions
    suspend fun insertEvent(event: Event) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertEvent(event)
        }
    }

    suspend fun getEvent(id: Int): Event? {
        return withContext(Dispatchers.IO) {
            studentDatabase.getEvent(id)
        }
    }

    suspend fun getAllEvents(): List<Event> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getAllEvents()
        }
    }

    suspend fun deleteAllEvents() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllEvents()
        }
    }

    //==========================================
    // ROOM - Exam Functions                  =
    //==========================================
    suspend fun insertExam(exam: Exam) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertExam(exam)
        }
    }

    suspend fun getExams(): List<Exam> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getExams()
        }
    }

    suspend fun getExam(id: Int): Exam? {
        return withContext(Dispatchers.IO) {
            studentDatabase.getExam(id)
        }
    }

    suspend fun getNewExams(): List<Exam> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getNewExams()
        }
    }

    suspend fun deleteAllExams() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllExams()
        }
    }

    suspend fun getExamsByClass(studentClass: String): List<Exam> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getExamsByClass(studentClass)
        }
    }

    //===========================================================================================
    //API                                                                                       =
    //===========================================================================================
    //student sign-in API                      =
    //==========================================
    suspend fun getStudentFromApi(studentUsername: String): Response<StudentResponse> {
        val studentResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.studentSignIn(studentUsername)
        }
        return studentResponse
    }

    //==========================================
    //Homework API                             =
    //==========================================
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
    suspend fun updateHomework(isCompleted: Boolean, filePath: String, id: Int) {
        withContext(Dispatchers.IO) {
            studentApi.updateHomework(isCompleted, filePath, id)
        }
    }

    //==========================================
    //parent Api                               =
    //==========================================
    suspend fun getParentFromApi(studentId: Int): Response<ParentResponse> {
        val parentResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.parent(studentId)
        }
        return parentResponse
    }

    //==========================================
    // calender api                            =
    //==========================================
    suspend fun getCalenderFromApi(): Response<CalenderSemesterListResponse> {
        val calenderResponse = withContext(Dispatchers.IO) {
            return@withContext studentApi.calenderSemester()
        }
        return calenderResponse
    }

    //==========================================
    //calender event api                       =
    //==========================================
    // API function for Event
    suspend fun getCalenderEventsFromApi(studentClass: String): Response<EventListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.calenderEvents(studentClass)
        }
    }

    //==========================================
    // API - Exam Functions                   =
    //==========================================
    suspend fun getExamCalenderFromApi(studentClass: String): Response<ExamListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.exams(studentClass)
        }
    }
}