package com.example.schoolapp.datasource.online.api

import com.example.schoolapp.datasource.online.response.CalenderSemesterListResponse
import com.example.schoolapp.datasource.online.response.CounselorCalenderListResponse
import com.example.schoolapp.datasource.online.response.ExamListResponse
import com.example.schoolapp.datasource.online.response.HomeworkListResponse
import com.example.schoolapp.datasource.online.response.MarksListResponse
import com.example.schoolapp.datasource.online.response.NotificationExamsListResponse
import com.example.schoolapp.datasource.online.response.StudentResponse
import com.example.schoolapp.datasource.online.response.NotificationProblemsListResponse
import com.example.schoolapp.datasource.online.response.SessionListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//PHP file name
const val studentApi = "api.php?"

//Handel the API responses
interface StudentDatabaseApi {

    //student sign-in API
    @GET(studentApi)
    suspend fun studentSignIn(
        @Query("endApiCall") endApiCall: String = "student_sigjn_in",
        @Query("studentUsername") studentUsername: String
    ): Response<StudentResponse>

    //notification problems API
    @GET(studentApi)
    suspend fun notificationProblems(
        @Query("studentId") studentId: Int,
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "notification_problems"
    ): Response<NotificationProblemsListResponse>

    //notification exams API
    @GET(studentApi)
    suspend fun notificationExams(
        @Query("studentId") studentId: Int,
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "notification_exams"
    ): Response<NotificationExamsListResponse>

    //homework API
    @GET(studentApi)
    suspend fun homeworks(
        @Query("studentClass") studentClass: String,
        @Query("endApiCall") endApiCall: String = "homeworks"
    ): Response<HomeworkListResponse>

    //profile API
    @GET(studentApi)
    suspend fun profile(
        @Query("studentId") studentId: Int,
        @Query("endApiCall") endApiCall: String = "profile"
    ): Response<StudentResponse>

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


    companion object {
        //logic for invoke the api
        operator fun invoke(): StudentDatabaseApi {
            return Retrofit.Builder()
                .baseUrl("http://16.24.157.106/mohammed99Ali/student_api/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentDatabaseApi::class.java)
        }
    }
}