package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Util.HomeworkCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.datasource.local.entity.Homework
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkPage(viewModel: MainViewModel, navController: NavController) {
    val homeworkListState = viewModel.homeworkList.collectAsState()
    val homeworkResponsesState = viewModel.homeworkResponses.collectAsState()
    val studentState = viewModel.student.collectAsState()
    val context = LocalContext.current

    var showCompletedHomeworks by remember { mutableStateOf(false) }
    var showOverdueHomeworks by remember { mutableStateOf(false) }

    // Effect to load homework responses when page opens
    LaunchedEffect(Unit) {
        studentState.value?.studentId?.let { studentId ->
            viewModel.fetchHomeworkResponses(studentId)
        }
    }

    val currentDate = LocalDate.now()

    // Filter homeworks into categories
    val (currentHomeworks, completedHomeworks, overdueHomeworks) = homeworkListState.value?.partition { homework ->
        val endDate = LocalDate.parse(homework.homeworkEndDate)
        val response = homeworkResponsesState.value?.find { it.homeworkId == homework.homeworkId }

        when {
            response?.isComplete == true -> false // Completed homeworks
            endDate.isBefore(currentDate) -> false // Overdue homeworks
            else -> true // Current homeworks
        }
    }?.let { (current, others) ->
        val (completed, overdue) = others.partition { homework ->
            homeworkResponsesState.value?.find { it.homeworkId == homework.homeworkId }?.isComplete == true
        }
        Triple(current, completed, overdue)
    } ?: Triple(emptyList(), emptyList(), emptyList())

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                    .height(170.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    IconButton(
                        modifier = Modifier.padding(top = 50.dp, start = 5.dp),
                        onClick = { navController?.popBackStack() }
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
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Current Homeworks Section - Always visible
            items(currentHomeworks.size) { index ->
                HomeworkCard(
                    homework = currentHomeworks[index],
                    response = homeworkResponsesState.value?.find {
                        it.homeworkId == currentHomeworks[index].homeworkId
                    },
                    viewModel = viewModel,
                    context = context
                )
            }

            // Completed Homeworks Section - Collapsible
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { showCompletedHomeworks = !showCompletedHomeworks },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (showCompletedHomeworks)
                                Icons.Default.KeyboardArrowDown
                            else
                                Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "الواجبات المنتهية",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            if (showCompletedHomeworks) {
                items(completedHomeworks.size) { index ->
                    HomeworkCard(
                        homework = completedHomeworks[index],
                        response = homeworkResponsesState.value?.find {
                            it.homeworkId == completedHomeworks[index].homeworkId
                        },
                        viewModel = viewModel,
                        context = context
                    )
                }
            }

            // Overdue Homeworks Section - Collapsible
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { showOverdueHomeworks = !showOverdueHomeworks },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (showOverdueHomeworks)
                                Icons.Default.KeyboardArrowDown
                            else
                                Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Text(
                            text = "الواجبات المتأخرة",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            if (showOverdueHomeworks) {
                items(overdueHomeworks.size) { index ->
                    HomeworkCard(
                        homework = overdueHomeworks[index],
                        response = homeworkResponsesState.value?.find {
                            it.homeworkId == overdueHomeworks[index].homeworkId
                        },
                        viewModel = viewModel,
                        context = context
                    )
                }
            }
        }
    }
}