package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolapp.Presentation.VM.States.CalenderState

@Composable
fun CalendarEmptyState(
    calenderState: CalenderState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = when (calenderState) {
                    CalenderState.EVENT_STATE -> "No upcoming events"
                    CalenderState.EXAM_STATE -> "No exams currently scheduled"
                    CalenderState.CALENDER_STATE -> "No semester schedule available"
                },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = when (calenderState) {
                    CalenderState.EVENT_STATE -> "Check back later for new school events"
                    CalenderState.EXAM_STATE -> "Exam schedule will be posted when available"
                    CalenderState.CALENDER_STATE -> "Semester schedule will be updated soon"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}