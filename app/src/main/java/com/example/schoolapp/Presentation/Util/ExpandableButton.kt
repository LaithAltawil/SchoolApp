package com.example.schoolapp.Presentation.Util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CalenderEventCard
import com.example.schoolapp.Presentation.Screens.ScreensPieces.EventCard
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ExamCalendarItem
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ExamCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.States.CalenderState

//=======================================================
// Calender UI Box                                      =
//=======================================================
@Composable
fun ExpandableButton(
    name: String,
    text: String,
    calenderState: CalenderState,
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val eventList = viewModel.eventList.collectAsState()
    val examList = viewModel.examList.collectAsState()
    val calenderEventList = viewModel.calenderEventList.collectAsState()
    val student = viewModel.student.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    // Get subjects based on class
    val subjects = when (student.value?.studentClass?.lowercase()) {
        "first" -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "علوم", "اجتماعيات"
        )
        "second", "third" -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "علوم", "اجتماعيات"
        )
        "fourth", "fifth", "sixth", "seventh", "eighth" -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "ثقافة مالية", "علوم",
            "تربية وطنية", "جغرافيا", "تاريخ"
        )
        "ninth", "tenth" -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "ثقافة مالية",
            "فيزياء", "احياء", "كيمياء", "علوم الأرض",
            "تربية وطنية", "جغرافيا", "تاريخ"
        )
        else -> emptyList()
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                Text(text = name, fontSize = 28.sp)
            }
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                when (calenderState) {
                    CalenderState.EVENT_STATE -> {
                        if (eventList.value.isNullOrEmpty()) {
                            EmptyStateMessage(
                                "لا احداث رئيسية",
                                "عد هنا لاحقا للتحقق من اي احداث مهمة"
                            )
                        } else {
                            eventList.value?.forEach { event ->
                                EventCard(
                                    event = event,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }

                    CalenderState.EXAM_STATE -> {
                        if (examList.value.isNullOrEmpty()) {
                            EmptyStateMessage(
                                "لا يوجد اختبارات",
                                "سوف يتم نشر جدول الامتحانات هنا عند توفره"
                            )
                        } else {
                            examList.value?.forEach { exam ->
                                ExamCalendarItem(
                                    exam = exam,
                                    subjects = subjects,
                                    navController = navController,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }

                    CalenderState.CALENDER_STATE -> {
                        if (calenderEventList.value.isNullOrEmpty()) {
                            EmptyStateMessage(
                                "لا جدول للفصل حاليا",
                                "سوف يتم نشر جدول الفصل هنا عند توفره"
                            )
                        } else {
                            calenderEventList.value?.forEach { calenderEvent ->
                                CalenderEventCard(
                                    calenderEvent = calenderEvent,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun EmptyStateMessage(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}