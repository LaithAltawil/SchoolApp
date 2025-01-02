package com.example.schoolapp.Presentation.Util


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schoolapp.datasource.local.entity.Problem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private fun Char.isArabic(): Boolean {
    val arabicRange = '\u0600'..'\u06FF'
    val arabicPresentationRange = '\uFB50'..'\uFDFF'
    val arabicExtendedRange = '\uFE70'..'\uFEFF'

    return this in arabicRange ||
            this in arabicPresentationRange ||
            this in arabicExtendedRange
}

fun String.hasArabicText() = any { it.isArabic() }

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@Composable
fun ProblemCard(
    problem: Problem,
    expanded: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(expanded) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            containerColor = if (!problem.problemStatus && problem.problemDiscussionDate == null) {
                MaterialTheme.colorScheme.primaryContainer
            } else if (!problem.problemStatus && problem.problemDiscussionDate != null) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.tertiaryContainer
            }
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "تاريخ تقديم المشكلة: ${
                            LocalDate.parse(problem.problemDate).format(
                                DateTimeFormatter.ofPattern("dd-MM-yyyy")
                            )
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                    Text(
                        text = "نوع المشكلة: ${problem.problemType}",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "تفاصيل المشكلة:",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                    Text(
                        text = problem.problemNotes,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = if (problem.problemNotes.any { it.isArabic() })
                            TextAlign.Right
                        else
                            TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (problem.problemDiscussionDate != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "موعد المناقشة: ${
                            LocalDate.parse(problem.problemDiscussionDate)
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                }

                if (problem.problemDiscussionSession != null) {
                    Text(
                        text = "حصة المناقشة: ${problem.problemDiscussionSession}",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}