package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolapp.datasource.local.entity.Exam
import androidx.compose.foundation.layout.Arrangement
import com.example.schoolapp.Presentation.Util.convertDateString

@Composable
fun ExamCard(exam: Exam, modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side - Subject
            Text(
                text = exam.examTeacherSubject,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            // Right side - Day and Date
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = exam.examDay,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = convertDateString(exam.examDate),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}