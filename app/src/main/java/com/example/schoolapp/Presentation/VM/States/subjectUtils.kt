package com.example.schoolapp.Presentation.VM.States

import com.example.schoolapp.R

enum class SubjectCategory {
    LANGUAGES,
    SCIENCES,
    MATHEMATICS,
    SOCIAL_STUDIES,
    ISLAMIC_STUDIES,
    PHYSICAL_EDUCATION,
    VOCATIONAL,
    FINANCIAL
}

data class SubjectInfo(
    val arabicName: String,
    val englishName: String,
    val iconResId: Int,
    val category: SubjectCategory
)

object SubjectUtils {
    val subjectMap = mapOf(
        "عربي" to SubjectInfo("عربي", "Arabic", R.drawable.arabic, SubjectCategory.LANGUAGES),
        "انجليزي" to SubjectInfo(
            "انجليزي",
            "English",
            R.drawable.english,
            SubjectCategory.LANGUAGES
        ),
        "رياضة" to SubjectInfo(
            "رياضة",
            "Physical Education",
            R.drawable.sports,
            SubjectCategory.PHYSICAL_EDUCATION
        ),
        "رياضيات" to SubjectInfo(
            "رياضيات",
            "Mathematics",
            R.drawable.math,
            SubjectCategory.MATHEMATICS
        ),
        "تربية اسلامية" to SubjectInfo(
            "تربية اسلامية",
            "Islamic Studies",
            R.drawable.math,
            SubjectCategory.ISLAMIC_STUDIES
        ),
        "تربية مهنية" to SubjectInfo(
            "تربية مهنية",
            "Vocational Education",
            R.drawable.math,
            SubjectCategory.VOCATIONAL
        ),
        "علوم" to SubjectInfo("علوم", "Science", R.drawable.science, SubjectCategory.SCIENCES),
        "اجتماعيات" to SubjectInfo(
            "اجتماعيات",
            "Social Studies",
            R.drawable.math,
            SubjectCategory.SOCIAL_STUDIES
        ),
        "ثقافة مالية" to SubjectInfo(
            "ثقافة مالية",
            "Financial Culture",
            R.drawable.math,
            SubjectCategory.FINANCIAL
        ),
        "تربية وطنية" to SubjectInfo(
            "تربية وطنية",
            "National Education",
            R.drawable.math,
            SubjectCategory.SOCIAL_STUDIES
        ),
        "جغرافيا" to SubjectInfo(
            "جغرافيا",
            "Geography",
            R.drawable.geography,
            SubjectCategory.SOCIAL_STUDIES
        ),
        "تاريخ" to SubjectInfo(
            "تاريخ",
            "History",
            R.drawable.history,
            SubjectCategory.SOCIAL_STUDIES
        ),
        "فيزياء" to SubjectInfo("فيزياء", "Physics", R.drawable.math, SubjectCategory.SCIENCES),
        "احياء" to SubjectInfo("احياء", "Biology", R.drawable.math, SubjectCategory.SCIENCES),
        "كيمياء" to SubjectInfo("كيمياء", "Chemistry", R.drawable.math, SubjectCategory.SCIENCES),
        "علوم الأرض" to SubjectInfo(
            "علوم الأرض",
            "Earth Science",
            R.drawable.math,
            SubjectCategory.SCIENCES
        )
    )

    fun getSubjectsForClass(classNumber: Int): List<String> {
        return when (classNumber) {
            1, 2, 3 -> listOf(
                "عربي", "انجليزي", "رياضة", "رياضيات",
                "تربية اسلامية", "تربية مهنية", "علوم", "اجتماعيات"
            )

            4, 5, 6, 7, 8 -> listOf(
                "عربي", "انجليزي", "رياضة", "رياضيات",
                "تربية اسلامية", "تربية مهنية", "ثقافة مالية", "علوم",
                "تربية وطنية", "جغرافيا", "تاريخ"
            )

            9, 10 -> listOf(
                "عربي", "انجليزي", "رياضة", "رياضيات",
                "تربية اسلامية", "تربية مهنية", "ثقافة مالية",
                "فيزياء", "احياء", "كيمياء", "علوم الأرض",
                "تربية وطنية", "جغرافيا", "تاريخ"
            )

            else -> emptyList()
        }
    }

    fun getSubjectInfo(arabicName: String): SubjectInfo {
        return subjectMap[arabicName] ?: SubjectInfo(
            arabicName = arabicName,
            englishName = arabicName,
            iconResId = R.drawable.baseline_broken,
            category = SubjectCategory.LANGUAGES
        )
    }
}