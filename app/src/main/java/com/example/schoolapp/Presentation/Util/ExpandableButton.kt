package com.example.schoolapp.Presentation.Util

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CalenderEventCard
import com.example.schoolapp.Presentation.Screens.ScreensPieces.EventCard
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
    modifier: Modifier = Modifier // Add modifier parameter for flexibility
) {
    val eventList = viewModel.eventList.collectAsState()
    val examList = viewModel.examList.collectAsState()
    val calenderEventList = viewModel.calenderEventList.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    // Remove the outer Column with fillMaxSize
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        // Button for expanding/collapsing
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
                Text(text = name, fontSize = 28.sp)
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
            }
        }

        // Animated content
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
                        eventList.value?.forEach { event ->
                            EventCard(
                                event = event,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                    CalenderState.EXAM_STATE -> {
                        examList.value?.forEach { exam ->
                            ExamCard(
                                exam = exam,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                    CalenderState.CALENDER_STATE -> {
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