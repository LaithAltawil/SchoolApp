package com.example.schoolapp.Presentation.Util


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolapp.datasource.local.entity.Problem

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@Composable
fun ProblemCard(
    problem: Problem,
    isAppointment: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            containerColor = when {
                isAppointment -> MaterialTheme.colorScheme.tertiaryContainer
                problem.problemStatus == 1 -> MaterialTheme.colorScheme.secondaryContainer
                else -> MaterialTheme.colorScheme.primaryContainer
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "problem.problemTitle",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = problem.problemType,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (isAppointment && problem.problemDiscussionSession != null) {
                    Text(
                        text = problem.problemDiscussionSession,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = problem.problemNotes,
                    style = MaterialTheme.typography.bodyMedium
                )

                if (isAppointment) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "تاريخ الموعد: ${problem.problemDiscussionDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "تاريخ التقديم: ${problem.problemDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}