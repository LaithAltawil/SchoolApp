package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.layout.Arrangement
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
import com.example.schoolapp.Presentation.Util.convertDateString
import com.example.schoolapp.datasource.local.entity.CalenderEvent

@Composable
fun CalenderEventCard(calenderEvent: CalenderEvent, modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Event Description
            Text(
                text = calenderEvent.eventDescription,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Dates section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Start Date Column
                Column {
                    Text(
                        text = "From",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = convertDateString(calenderEvent.eventStartDate),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = calenderEvent.eventStartDay,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                // End Date Column
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "To\t\t\t\t\t\t\t\t",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = convertDateString(calenderEvent.eventEndDate),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}