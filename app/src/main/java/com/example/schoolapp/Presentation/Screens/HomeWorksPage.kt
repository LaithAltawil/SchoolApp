package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Util.HomeworkCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.time.LocalDate


//=======================================================
//home work page: UI & logic                            =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkPage(viewModel: MainViewModel, navController: NavController, opened: Int? = null) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val homeworkListState = viewModel.homeworkList.collectAsState()
    val homeworkResponsesState = viewModel.homeworkResponses.collectAsState() // New state for responses
    val studentState = viewModel.student.collectAsState()
    var showFinishedHomeworks by remember { mutableStateOf(false) }
    var showOverdueHomeworks by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Effect to load homework responses when page opens
    LaunchedEffect(Unit) {
        studentState.value?.studentId?.let { studentId ->
            viewModel.fetchHomeworkResponses(studentId)
        }
    }

    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                            .height(170.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            IconButton(
                                modifier = Modifier.padding(top = 50.dp, start = 5.dp),
                                onClick = { navController.popBackStack() }
                            ) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    contentDescription = "Back",
                                    modifier = Modifier.size(50.dp),
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "واجباتي",
                                fontSize = 70.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                modifier = Modifier.padding(top = 40.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Current Homeworks
                    Text(
                        "Current Homeworks",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    LazyColumn {
                        if (!homeworkListState.value.isNullOrEmpty()) {
                            items(homeworkListState.value!!.size) { index ->
                                val homework = homeworkListState.value!![index]
                                val response = homeworkResponsesState.value?.find {
                                    it.homeworkId == homework.homeworkId
                                }

                                HomeworkCard(
                                    homework = homework,
                                    response = response,
                                    viewModel = viewModel,
                                    context = context
                                )
                            }
                        } else {
                            item {
                                Text(
                                    "No current homework available",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    // Finished Homeworks Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Finished Homeworks",
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showFinishedHomeworks = !showFinishedHomeworks }
                                .padding(16.dp)
                        )

                        if (showFinishedHomeworks) {
                            val finishedHomeworks = homeworkListState.value?.filter { homework ->
                                homeworkResponsesState.value?.any {
                                    it.homeworkId == homework.homeworkId && it.isComplete
                                } ?: false
                            }

                            if (finishedHomeworks.isNullOrEmpty()) {
                                Text(
                                    "No finished homeworks",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            } else {
                                finishedHomeworks.forEach { homework ->
                                    val response = homeworkResponsesState.value?.find {
                                        it.homeworkId == homework.homeworkId
                                    }
                                    HomeworkCard(
                                        homework = homework,
                                        response = response,
                                        viewModel = viewModel,
                                        context = context
                                    )
                                }
                            }
                        }
                    }

                    // Overdue Homeworks Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Overdue Homeworks",
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showOverdueHomeworks = !showOverdueHomeworks }
                                .padding(16.dp)
                        )

                        if (showOverdueHomeworks) {
                            val currentDate = LocalDate.now()
                            val overdueHomeworks = homeworkListState.value?.filter { homework ->
                                val endDate = LocalDate.parse(homework.homeworkEndDate)
                                val response = homeworkResponsesState.value?.find {
                                    it.homeworkId == homework.homeworkId
                                }
                                endDate.isBefore(currentDate) && response?.isComplete != true
                            }

                            if (overdueHomeworks.isNullOrEmpty()) {
                                Text(
                                    "No overdue homeworks",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            } else {
                                overdueHomeworks.forEach { homework ->
                                    val response = homeworkResponsesState.value?.find {
                                        it.homeworkId == homework.homeworkId
                                    }
                                    HomeworkCard(
                                        homework = homework,
                                        response = response,
                                        viewModel = viewModel,
                                        context = context
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
