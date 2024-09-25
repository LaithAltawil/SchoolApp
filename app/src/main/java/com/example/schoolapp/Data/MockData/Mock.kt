package com.example.schoolapp.Data.MockData

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.homework


object Mock {
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

    val daysOfWeek =
        listOf("Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday")

    data class Class(
        val subjectName: String,
        val teacher: String,
        val time: String
    )

    val classList = listOf(
        Class("Math", "Mr.Smith", "9:00 AM"),
        Class("Science", "Ms.Johnson", "10:30 AM"),
        Class("English", "Mrs.Williams", "1:00 PM"),
        Class("History", "Mr.Brown", "2:30 PM")
        // Add more classes as needed
    )


}
