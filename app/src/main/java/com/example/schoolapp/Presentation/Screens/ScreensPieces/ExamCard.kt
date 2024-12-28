package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolapp.Presentation.VM.States.SubjectUtils
import com.example.schoolapp.datasource.local.entity.Exam

import java.time.LocalDate

@Composable
fun ExamCard(
    exam: Exam,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onExpandClick: () -> Unit = {}
) {
    val subjectInfo = SubjectUtils.getSubjectInfo(exam.examTeacherSubject)
    val isUpcoming = try {
        val examDate = LocalDate.parse(exam.examDate)
        val today = LocalDate.now()
        examDate.isAfter(today) || examDate.isEqual(today)
    } catch (e: Exception) {
        true
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onExpandClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Subject Icon
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Image(
                            painter = painterResource(id = subjectInfo.iconResId),
                            contentDescription = subjectInfo.englishName,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                        )
                    }

                    // Subject Name
                    Column {
                        Text(
                            text = subjectInfo.arabicName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = subjectInfo.englishName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                // Status Chip
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (isUpcoming)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = if (isUpcoming) "قادم" else "سابق",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                // Exam Details
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Date and Day
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DetailItem(
                            label = "التاريخ",
                            value = exam.examDate
                        )
                        DetailItem(
                            label = "اليوم",
                            value = exam.examDay
                        )
                    }

                    // Materials
                    if (!exam.examMaterial.isNullOrBlank()) {
                        DetailItem(
                            label = "المواد المطلوبة",
                            value = exam.examMaterial
                        )
                    }

                    // Notes
                    if (!exam.examNotes.isNullOrBlank()) {
                        DetailItem(
                            label = "ملاحظات",
                            value = exam.examNotes
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}