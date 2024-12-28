package com.example.schoolapp.datasource.online.api

import com.example.schoolapp.datasource.online.response.CalenderSemesterListResponse
import com.example.schoolapp.datasource.online.response.CounselorCalenderListResponse
import com.example.schoolapp.datasource.online.response.EventListResponse
import com.example.schoolapp.datasource.online.response.ExamListResponse
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.MarksListResponse
import com.example.schoolapp.datasource.online.response.NotificationExamsListResponse
import com.example.schoolapp.datasource.online.response.NotificationProblemsListResponse
import com.example.schoolapp.datasource.online.response.ParentResponse
import com.example.schoolapp.datasource.online.response.SessionListResponse
import com.example.schoolapp.datasource.online.response.StudentResponse
import com.example.schoolapp.datasource.online.response.TeacherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//PHP file name
const val studentApi = "api.php?"

//Handel the API responses
interface StudentDatabaseApi {

    //student sign-in API
    @GET(studentApi)
    suspend fun studentSignIn(
        @Query("studentUsername") studentUsername: String,
        @Query("endApiCall") endApiCall: String = "student_sign_in",
    ): Response<StudentResponse>

    //parent API
    @GET(studentApi)
    suspend fun parent(
        @Query("studentId") studentId: Int,
        @Query("endApiCall") endApiCall: String = "profile",
    ): Response<ParentResponse>

    //notification problems API
    @GET(studentApi)
    suspend fun notificationProblems(
        @Query("studentId") studentId: Int,
        @Query("endApiCall") endApiCall: String = "notification_problems"
    ): Response<NotificationProblemsListResponse>

    //notification exams API
    @GET(studentApi)
    suspend fun notificationExams(
        @Query("studentId") studentId: Int,
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "notification_exams"
    ): Response<NotificationExamsListResponse>

    // In StudentDatabaseApi.kt
    @GET(studentApi)
    suspend fun calenderEvents(
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "calender_event"
    ): Response<EventListResponse>

    //homework API
    @GET(studentApi)
    suspend fun homeworks(
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "homeworks"
    ): Response<HomeworkListResponse>

    //homework (by Id) API
    @GET(studentApi)
    suspend fun homeworkById(
        @Query("studentClass") studentClass: String,
        @Query("homeworkId") homeworkId: Int,
        @Query("endApiCall") endApiCall: String = "homeworks_by_id"
    ): Response<HomeworkListResponse>

    //update homework
    @GET(studentApi)
    suspend fun updateHomework(
        @Query("homeworkIsCompleted") homeworkIsCompleted: Boolean,
        @Query("homeworkFilePath") homeworkFilePath: String,
        @Query("homeworkId") homeworkId: Int,
        @Query("endApiCall") endApiCall: String = "update_homework"
    )

    //calender of the semester API
    @GET(studentApi)
    suspend fun calenderSemester(
        @Query("endApiCall") endApiCall: String = "calender_semester"
    ): Response<CalenderSemesterListResponse>

    //counselor calender API
    @GET(studentApi)
    suspend fun counselorCalender(
        @Query("studentId") studentId: Int,
        @Query("endApiCall") endApiCall: String = "counselor_calender"
    ): Response<CounselorCalenderListResponse>

    //Exams data API
    @GET(studentApi)
    suspend fun exams(
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "exam_calender"
    ): Response<ExamListResponse>

    //marks API
    @GET(studentApi)
    suspend fun marks(
        @Query("studentId") studentId: Int,
        @Query("endApiCall") endApiCall: String = "marks"
    ): Response<MarksListResponse>

    //sessions API
    @GET(studentApi)
    suspend fun sessions(
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "sessions"
    ): Response<SessionListResponse>

    //teacher API
    @GET(studentApi)
    suspend fun teacher(
        @Query("teacherId") teacherId: Int,
        @Query("endApiCall") endApiCall: String = "teacher",
    ): Response<TeacherResponse>

    companion object {
        operator fun invoke(): StudentDatabaseApi {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://16.24.157.106/mohammed99Ali/student_api/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentDatabaseApi::class.java)
        }
    }
}