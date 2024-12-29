package com.example.schoolapp.Presentation.VM.States

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.painter.Painter
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.Homework
import java.time.LocalDate

//Data source
sealed class MainDataClass{

    //todo @LT #simple|| "Id" [13:13] must start with small latter
    //todo @LT #simple|| add usage
    data class CounselorState(
        val name: String = "",
        val selectedDate: LocalDate = LocalDate.now(),
        val time: String = "",
        val isLoading: Boolean = false,
        val error: String? = null,
        val showContactDialog: Boolean = false
    ) :MainDataClass()

    //todo @LT #simple|| "subjects" [22:21] must start with small latter
    //todo @LT #simple|| add usage
    data class ExamPageState1(
        val Subjects: List<String> = listOf(
            "Math",
            "Science",
            "History",
            "English",
            "Spanish",
            "French",
            "Arabic",
            "Computer Science"
        ),

        var bottomSheet: MutableList<Boolean> = mutableStateListOf(
            false, false, false, false, false, false, false,
            false, false, false, false, false, false, false
        )
    ) : MainDataClass()

    data class setting(
        val name: String,
        val imagePath: Painter?,
        val description: String,
        val onAction: () -> Unit
    ):MainDataClass()

    //todo @LT #simple|| add usage
    data class HomeworkPageState1(
        val homework: List<Homework> = emptyList(),
        val isTopBarVisible: Boolean = false,
    ) : MainDataClass()

    data class MarksPageState1(
        val subjects: List<String> = listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "علوم", "اجتماعيات",
            "ثقافة مالية", "تربية وطنية", "جغرافيا", "تاريخ",
            "فيزياء", "احياء", "كيمياء", "علوم الأرض"
        ),
        val marksItems: List<Subjects> = listOf(
            Subjects("عربي", onClick = { }),
            Subjects("انجليزي", onClick = { }),
            Subjects("رياضة", onClick = { }),
            Subjects("رياضيات", onClick = { }),
            Subjects("تربية اسلامية", onClick = { }),
            Subjects("تربية مهنية", onClick = { }),
            Subjects("علوم", onClick = { }),
            Subjects("اجتماعيات", onClick = { }),
            Subjects("ثقافة مالية", onClick = { }),
            Subjects("تربية وطنية", onClick = { }),
            Subjects("جغرافيا", onClick = { }),
            Subjects("تاريخ", onClick = { }),
            Subjects("فيزياء", onClick = { }),
            Subjects("احياء", onClick = { }),
            Subjects("كيمياء", onClick = { }),
            Subjects("علوم الأرض", onClick = { })
        ),
        val isTopBarVisible: Boolean = false,
        var bottomSheet: MutableList<Boolean> = mutableStateListOf(
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false  // Extra capacity just in case
        )
    ) : MainDataClass()

    //todo @LT #simple|| add usage
    data class SettingsPageState1(
        var showAlertDialog: MutableList<Boolean>
        = mutableStateListOf(false, false, false, false, false, false, false)
    ) :MainDataClass()

    //todo @LT #simple|| "subjects" [82:13] must start with small latter
    //todo @LT #simple|| add usage
    data class ResourcesPageState(
        val isTopAppBarVisible: Boolean = false,
        val Subjects: List<Subjects> = listOf(
            Subjects("Maths",onClick = { }) ,
            Subjects("Science",onClick = { }) ,
            Subjects("English",onClick = { }) ,
            Subjects("History",onClick = { }) ,
            Subjects("Arabic",onClick = { }) ,
            Subjects("Computer Science",onClick = { }) ,
            Subjects("Geography",onClick = { })
        ),
        var BottomSheet1: MutableList<Boolean>
        = mutableStateListOf(false, false, false, false, false, false, false)
    ) : MainDataClass()

    //todo @LT #simple|| add usage
    data class ClassesPageState(
        val isTopAppBarVisible: Boolean = false,
    ) : MainDataClass()

    //todo @LT #simple|| add usage
    data class CalenderPage(
        val isTopAppBarVisible: Boolean = false,
        ) : MainDataClass()
}