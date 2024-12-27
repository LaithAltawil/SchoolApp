package com.example.schoolapp.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.schoolapp.datasource.local.dao.CalenderDao
import com.example.schoolapp.datasource.local.dao.ExamDao
import com.example.schoolapp.datasource.local.dao.HomeworkDao
import com.example.schoolapp.datasource.local.dao.ParentDao
import com.example.schoolapp.datasource.local.dao.StudentDao
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Exam
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Student

@Database(
    entities = [Student::class,
        Homework::class,
        Parent::class,
        Event::class,
        Exam::class,
        CalenderEvent::class],
    version = 12,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao
    abstract val homeworkDao: HomeworkDao
    abstract val parentDao: ParentDao
    abstract val calenderDao: CalenderDao
    abstract val examDao: ExamDao

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
    //= Parent function's                                   =
    //= Calender function's                                 =
    //= Exam's function                                     =
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
    suspend fun debugHomeworkDates() = homeworkDao.debugHomeworkDates()

    // Parent function's
    suspend fun insertParent(parent: Parent) = parentDao.insert(parent)
    suspend fun setParent() = parentDao.setParent()
    suspend fun deleteParent() = parentDao.deleteParent()

    // Calender function's
    suspend fun insertCalenderEvent(calenderEvent: CalenderEvent) =
        calenderDao.insertCalenderEvent(calenderEvent)

    suspend fun getCalenderEvent(id: Int) = calenderDao.getCalenderEvent(id)
    suspend fun getCalenderEvents() = calenderDao.getCalenderEvents()
    suspend fun deleteAllCalenderEvents() = calenderDao.deleteAllCalenderEvents()
    suspend fun insertEvent(event: Event) = calenderDao.insert(event)
    suspend fun getEvent(id: Int) = calenderDao.getEvent(id)
    suspend fun getAllEvents() = calenderDao.getAllEvents()
    suspend fun deleteAllEvents() = calenderDao.deleteAllEvents()

    //  Exam's functions
    suspend fun insertExam(exam: Exam) = examDao.insertExam(exam)
    suspend fun getExams() = examDao.getExams()
    suspend fun getExam(id: Int) = examDao.getExam(id)
    suspend fun getNewExams() = examDao.getNewExams()
    suspend fun getExamsByClass(studentClass: String) = examDao.getExamsByClass(studentClass)
    suspend fun deleteAllExams() = examDao.deleteAllExams()

}