package com.example.schoolapp.datasource.repository

import com.example.schoolapp.datasource.local.dao.ExamDao
import com.example.schoolapp.datasource.local.dao.HomeworkDao
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Exam
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Session
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.api.StudentDatabaseApi
import com.example.schoolapp.datasource.online.response.CalenderSemesterListResponse
import com.example.schoolapp.datasource.online.response.EventListResponse
import com.example.schoolapp.datasource.online.response.ExamListResponse
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.ParentResponse
import com.example.schoolapp.datasource.online.response.SessionListResponse
import com.example.schoolapp.datasource.online.response.StudentResponse
import com.example.schoolapp.datasource.online.response.TeacherResponse
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

    suspend fun debugHomeworkDates(): List<HomeworkDao.DebugHomework> {
        val homeworkList = withContext(Dispatchers.IO) {
            return@withContext studentDatabase.debugHomeworkDates()
        }
        return homeworkList
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

    //==========================================
    //Sessions ROOM                            =
    //==========================================
    suspend fun insertSession(session: Session) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertSession(session)
        }
    }

    suspend fun insertAllSessions(sessions: List<Session>) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertAllSessions(sessions)
        }
    }

    suspend fun getSessionsByClass(classId: String): List<Session> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getSessionsByClass(classId)
        }
    }

    suspend fun getSessionsByDay(day: String): List<Session> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getSessionsByDay(day)
        }
    }

    suspend fun deleteAllSessions() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllSessions()
        }
    }

    suspend fun deleteSessionsByClass(classId: String) {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteSessionsByClass(classId)
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

    //==========================================
    // session api                             =
    //==========================================
    suspend fun getSessionsFromApi(studentClass: String): Response<SessionListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.sessions(studentClass)
        }
    }

    // Function to fetch and store sessions
    suspend fun refreshSessions(studentClass: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = getSessionsFromApi(studentClass)
                if (response.isSuccessful && response.body() != null && !response.body()!!.error) {
                    // Clear existing sessions for this class
                    deleteSessionsByClass(studentClass)

                    // Convert API models to local entities and insert
                    val sessions = response.body()!!.sessions.map { sessionModel ->
                        Session(
                            sessionId = 0,
                            sessionTeacherClass = sessionModel.sessionTeacherClass,
                            day = sessionModel.day,
                            session = sessionModel.session,
                            sessionTeacherSubject = sessionModel.sessionTeacherSubject,
                            sessionTeacherId = sessionModel.sessionTeacherId
                        )
                    }
                    insertAllSessions(sessions)
                }
            } catch (e: Exception) {
                // Handle network errors or other exceptions
                e.printStackTrace()
            }
        }
    }

    //===========================================
    //teacher                                   =
    //===========================================
    //teacher Api
    suspend fun getTeacherFromApi(teacherId: Int): Response<TeacherResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.teacher(teacherId)
        }
    }

}