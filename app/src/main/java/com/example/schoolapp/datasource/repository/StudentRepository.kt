package com.example.schoolapp.datasource.repository

import android.util.Log
import com.example.schoolapp.datasource.local.dao.HomeworkDao
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Exam
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Mark
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Problem
import com.example.schoolapp.datasource.local.entity.Session
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.api.StudentDatabaseApi
import com.example.schoolapp.datasource.online.response.CalenderSemesterListResponse
import com.example.schoolapp.datasource.online.response.DefaultResponse
import com.example.schoolapp.datasource.online.response.EventListResponse
import com.example.schoolapp.datasource.online.response.ExamListResponse
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.HomeworkResponseListResponse
import com.example.schoolapp.datasource.online.response.ParentResponse
import com.example.schoolapp.datasource.online.response.ProblemListResponse
import com.example.schoolapp.datasource.online.response.ProblemResponse
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

    //===============================================
    //marks dao's fun                               =
    //===============================================

    // Insert mark into local database
    suspend fun insertMark(mark: Mark) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertMark(mark)
        }
    }

    // Get all marks for a student from local database
    suspend fun getMarks(studentId: Int): List<Mark> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getMarks(studentId)
        }
    }

    // Delete marks for a specific student
    suspend fun deleteMarks(studentId: Int) {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteMarks(studentId)
        }
    }

    // Delete all marks
    suspend fun deleteAllMarks() {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteAllMarks()
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

    //===============================================
    //marks API fun                                 =
    //===============================================
    // Get marks from API and store in local database
    private suspend fun fetchAndStoreMarks(studentId: Int) {
        withContext(Dispatchers.IO) {
            try {
                // First delete existing marks for this student
                deleteMarks(studentId)

                // Fetch marks from API
                val response = studentApi.marks(studentId)

                if (!response.isSuccessful) {
                    Log.e("StudentRepository", "Error fetching marks: ${response.message()}")
                    return@withContext
                }

                val marksResponse = response.body()
                if (marksResponse?.error == true) {
                    Log.e("StudentRepository", "API returned error: ${marksResponse.message}")
                    return@withContext
                }

                // Store each mark in local database
                marksResponse?.marks?.forEach { markData ->
                    val mark = Mark(
                        markStudentId = markData.markStudentId,
                        markTeacherSubject = markData.markTeacherSubject,
                        firstMark = markData.firstMark,
                        secondMark = markData.secondMark,
                        thirdMark = markData.thirdMark,
                        forthMark = markData.forthMark,
                        totalMark = markData.totalMark
                    )
                    insertMark(mark)
                }
            } catch (e: Exception) {
                Log.e("StudentRepository", "Error in fetchAndStoreMarks", e)
            }
        }
    }

    // Sync marks - fetch fresh data from API and update local storage
    suspend fun syncMarks(studentId: Int) {
        withContext(Dispatchers.IO) {
            try {
                fetchAndStoreMarks(studentId)
            } catch (e: Exception) {
                Log.e("StudentRepository", "Error syncing marks", e)
            }
        }
    }

    // Get marks with automatic sync
    // In StudentRepository.kt
    suspend fun getMarksWithSync(studentId: Int) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("MarksDebug", "Fetching marks for student ID: $studentId")
                // First delete existing marks for this student
                deleteMarks(studentId)
                Log.d("MarksDebug", "Deleted existing marks for student")

                // Fetch marks from API
                val response = studentApi.marks(studentId)
                Log.d("MarksDebug", "API Response: ${response.body()}")

                if (!response.isSuccessful) {
                    Log.e("MarksDebug", "Error fetching marks: ${response.message()}")
                    return@withContext
                }

                val marksResponse = response.body()
                if (marksResponse?.error == true) {
                    Log.e("MarksDebug", "API returned error: ${marksResponse.message}")
                    return@withContext
                }

                // Log the number of marks received
                Log.d("MarksDebug", "Number of marks received: ${marksResponse?.marks?.size}")

                marksResponse?.marks?.forEachIndexed { index, markData ->
                    val mark = Mark(
                        markStudentId = studentId,
                        markTeacherSubject = markData.markTeacherSubject,
                        firstMark = markData.firstMark,
                        secondMark = markData.secondMark,
                        thirdMark = markData.thirdMark,
                        forthMark = markData.forthMark,
                        totalMark = markData.totalMark
                    )
                    Log.d("MarksDebug", "Attempting to insert mark $index: $mark")
                    insertMark(mark)
                    Log.d("MarksDebug", "Successfully inserted mark $index")
                }

                // Verify marks after insertion
                val savedMarks = getMarks(studentId)
                Log.d("MarksDebug", "Total marks saved in Room: ${savedMarks.size}")
                savedMarks.forEach { mark ->
                    Log.d("MarksDebug", "Saved mark: $mark")
                }

            } catch (e: Exception) {
                Log.e("MarksDebug", "Error in getMarksWithSync", e)
                e.printStackTrace()
            }
        }
    }

    suspend fun getHomeworkResponses(studentId: Int): Response<HomeworkResponseListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.getHomeworkResponses(studentId)
        }
    }

    suspend fun submitHomeworkResponse(
        homeworkId: Int,
        studentId: Int,
        filePath: String
    ): Response<DefaultResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.submitHomeworkResponse(homeworkId, studentId, filePath)
        }
    }

    //===========================================
// Problem ROOM Functions                  =
//===========================================
    suspend fun insertProblem(problem: Problem) {
        withContext(Dispatchers.IO) {
            studentDatabase.insertProblem(problem)
        }
    }

    suspend fun getStudentProblems(studentId: Int): List<Problem> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getStudentProblems(studentId)
        }
    }

    suspend fun getActiveProblems(studentId: Int): List<Problem> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getActiveProblems(studentId)
        }
    }

    suspend fun getResolvedProblems(studentId: Int): List<Problem> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getResolvedProblems(studentId)
        }
    }

    suspend fun deleteStudentProblems(studentId: Int) {
        withContext(Dispatchers.IO) {
            studentDatabase.deleteStudentProblems(studentId)
        }
    }

    suspend fun updateProblem(problem: Problem) {
        withContext(Dispatchers.IO) {
            studentDatabase.updateProblem(problem)
        }
    }

    suspend fun getScheduledProblems(studentId: Int): List<Problem> {
        return withContext(Dispatchers.IO) {
            studentDatabase.getScheduledProblems(studentId)
        }
    }

    //===========================================
    // Problem API Functions                   =
    //===========================================
    suspend fun submitProblemToApi(
        studentId: Int,
        problemType: String,
        problemNotes: String,
        problemDate: String
    ): Response<ProblemResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.submitProblem(
                studentId = studentId,
                problemType = problemType,
                problemNotes = problemNotes,
                problemDate = problemDate
            )
        }
    }

    suspend fun getStudentProblemsFromApi(studentId: Int): Response<ProblemListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.getStudentProblems(studentId)
        }
    }

    suspend fun getScheduledMeetingsFromApi(studentId: Int): Response<ProblemListResponse> {
        return withContext(Dispatchers.IO) {
            studentApi.getScheduledMeetings(studentId)
        }
    }

    // Add sync function to manage local storage
    suspend fun syncProblemData(studentId: Int) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("ProblemDebug", "Starting syncProblemData for student: $studentId")

                // Get problems from API
                val problemsResponse = getStudentProblemsFromApi(studentId)
                Log.d("ProblemDebug", "API Response: ${problemsResponse.body()}")

                if (problemsResponse.isSuccessful) {
                    // Clear existing problems
                    Log.d("ProblemDebug", "Clearing existing problems...")
                    deleteStudentProblems(studentId)

                    // Insert new problems
                    val problems = problemsResponse.body()?.problems ?: emptyList()
                    Log.d("ProblemDebug", "Inserting ${problems.size} problems from API")

                    problems.forEach { problemModel ->
                        val problem = Problem(
                            problemId = problemModel.problemId,
                            studentIdProblem = problemModel.studentIdProblem,
                            problemType = problemModel.problemType,
                            problemNotes = problemModel.problemNotes,
                            problemDate = problemModel.problemDate,
                            problemStatus = problemModel.problemStatus,
                            problemDiscussionDate = problemModel.problemDiscussionDate,
                            problemDiscussionSession = problemModel.problemDiscussionSession
                        )
                        Log.d("ProblemDebug", "Inserting problem: $problem")
                        insertProblem(problem)
                    }
                    Log.d("ProblemDebug", "Problems sync complete")
                } else {
                    Log.e("ProblemDebug", "API error: ${problemsResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ProblemDebug", "Error syncing problem data", e)
                throw e
            }
        }
    }
}