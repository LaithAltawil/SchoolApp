package com.example.schoolapp.Data.MockData

import com.example.schoolapp.Data.homework

object Mock {
    val HomeworkMock = listOf<homework>(

        homework("Math",
            "Solve equations 1-20",
            "2023-12-24",
            false),

        homework("Math",
            "Complete worksheet on fractions",
            "2023-12-28",
            true),
        homework("Science",
            "Chapter 5 review questions",
            "2024-01-05",
            false),
        homework("Science",
            "Prepare for science fair project",
            "2024-01-15", false),
        homework("History",
            "Read chapter 3 and write a summary", "2023-12-22",
            true),
        homework("English",
            "Essay on Shakespeare's Hamlet",
            "2024-01-10",
            false),
        homework("English",
            "Poetry analysis worksheet",
            "2023-12-29",
            false)
    )

}
