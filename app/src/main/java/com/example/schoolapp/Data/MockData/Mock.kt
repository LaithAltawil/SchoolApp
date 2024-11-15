package com.example.schoolapp.Data.MockData

import com.example.schoolapp.Data.Class
import com.example.schoolapp.Data.Exam
import com.example.schoolapp.Data.Homework

//=======================================================
//Object class for mock data                            =
//=======================================================
object Mock {

    //todo @MAS #medium || insert the complete homework mock data after the related task is finished
    val HomeworkMock = listOf<Homework>(

        Homework(
            "Math",
            "Solve equations 1-20",
            "2023-12-24",
            false
        ),

        Homework(
            "Math",
            "Complete worksheet on fractions",
            "2023-12-28",
            true
        ),
        Homework(
            "Science",
            "Chapter 5 review questions",
            "2024-01-05",
            false
        ),
        Homework(
            "Science",
            "Prepare for science fair project",
            "2024-01-15", false
        ),
        Homework(
            "History",
            "Read chapter 3 and write a summary", "2023-12-22",
            true
        ),
        Homework(
            "English",
            "Essay on Shakespeare's Hamlet",
            "2024-01-10",
            false
        ),
        Homework(
            "English",
            "Poetry analysis worksheet",
            "2023-12-29",
            false
        )
    )

    //todo @LS #qustion[not answered] || what is this 😀?
    //todo @LT #simple || fix the name issue... recommended solution: "profilePageTable"
    //done
    val profilePageTable = listOf(
        listOf("name :- ", "Laith"),
        listOf("email :- ", "john.tyler@examplepetstore.com"),
        listOf("phone :- ", "0123456789"),
        listOf("address :- ", "123 Main Street"),
        listOf("gender :- ", "Male"),
        listOf("dob :- ", "01/01/2000"),
        listOf("nationality :- ", "Kenyan"),
        listOf("language :- ", "English"),
        listOf("religion :- ", "Christian"),
    )

    val daysOfWeek = listOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday"
    )


    //todo @MAS #meduim || recreate data class to match the database
    //todo @LT #simple || please put the data class in the data classes file(named currently homework.kt)
    //done


    //todo @MAS #medium || insert the complete classList mock data after the related task is finished
    val classList = listOf(
        Class("Math", "Mr.Smith", "9:00 AM"),
        Class("Science", "Ms.Johnson", "10:30 AM"),
        Class("English", "Mrs.Williams", "1:00 PM"),
        Class("History", "Mr.Brown", "2:30 PM")
        // Add more classes as needed
    )

    val mockExamList = listOf(
        Exam("Mathematics", "2023-12-15", "10:00 AM", "Room A101"),
        Exam("Physics", "2023-12-16", "02:00 PM", "Room B202"),
        Exam("Chemistry", "2023-12-17", "09:00 AM", "Lab C303"),
        Exam("Biology", "2023-12-18", "11:00 AM", "Hall D404"),
        Exam("Computer Science", "2023-12-19", "03:00 PM", "Room E505")
    )


}