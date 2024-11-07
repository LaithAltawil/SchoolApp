package com.example.schoolapp.Data.MockData

import com.example.schoolapp.Data.homework

//=======================================================
//Object class for mock data                            =
//=======================================================
object Mock {

    //todo @MAS #medium || insert the complete homework mock data after the related task is finished
    val HomeworkMock = listOf<homework>(

        homework(
            "Math",
            "Solve equations 1-20",
            "2023-12-24",
            false
        ),

        homework(
            "Math",
            "Complete worksheet on fractions",
            "2023-12-28",
            true
        ),
        homework(
            "Science",
            "Chapter 5 review questions",
            "2024-01-05",
            false
        ),
        homework(
            "Science",
            "Prepare for science fair project",
            "2024-01-15", false
        ),
        homework(
            "History",
            "Read chapter 3 and write a summary", "2023-12-22",
            true
        ),
        homework(
            "English",
            "Essay on Shakespeare's Hamlet",
            "2024-01-10",
            false
        ),
        homework(
            "English",
            "Poetry analysis worksheet",
            "2023-12-29",
            false
        )
    )

    //todo @LS #qustion[not answered] || what is this 😀?
    //todo @LT #simple || fix the name issue... recommended solution: "profilePageTable"
    val profilepagetable = listOf(
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
    data class Class(
        val subjectName: String,
        val teacher: String,
        val time: String
    )

    //todo @MAS #medium || insert the complete classList mock data after the related task is finished
    val classList = listOf(
        Class("Math", "Mr.Smith", "9:00 AM"),
        Class("Science", "Ms.Johnson", "10:30 AM"),
        Class("English", "Mrs.Williams", "1:00 PM"),
        Class("History", "Mr.Brown", "2:30 PM")
        // Add more classes as needed
    )

}