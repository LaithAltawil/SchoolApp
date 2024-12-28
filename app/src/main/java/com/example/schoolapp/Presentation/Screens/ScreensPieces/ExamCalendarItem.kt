package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.datasource.local.entity.Exam

@Composable
fun ExamCalendarItem(
    exam: Exam,
    subjects: List<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Find the subject index
                val subjectIndex = subjects.indexOfFirst { it == exam.examTeacherSubject }
                if (subjectIndex != -1) {
                    // Navigate to exams page using the correct route
                    navController.navigate(Screen.ExamsPage.route) {
                        launchSingleTop = true
                    }
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = exam.examTeacherSubject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "التاريخ: ${exam.examDate}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
            )
            Text(
                text = exam.examDay,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
            )
        }
    }
}