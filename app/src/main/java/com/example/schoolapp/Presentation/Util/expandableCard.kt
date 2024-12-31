package com.example.schoolapp.Presentation.Util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schoolapp.Data.variable.opened
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.datasource.local.entity.Homework
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@Composable
fun ExpandableCard(
    homework: Homework,
    viewModel: MainViewModel,
    context: Context,
    onClick: () -> Unit
) {
    val homeworkResponsesState = viewModel.homeworkResponses.collectAsState()
    val response = homeworkResponsesState.value?.find {
        it.homeworkId == homework.homeworkId
    }

    // Only show if there's no response or the homework isn't complete
    if (response?.isComplete != true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    opened = homework.homeworkId
                    onClick()
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp)
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(homework.homeworkEndDay)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = homework.homeworkEndDate,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("اخر يوم تسليم")
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = homework.homeworkTeacherSubject,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}