package com.example.schoolapp.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.States.ExamLoadingState
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamsPage(mainViewModel: MainViewModel, navController: NavController) {
    val examState = mainViewModel.Examstate.collectAsStateWithLifecycle()
    val student = mainViewModel.student.collectAsState()
    val examList = mainViewModel.examList.collectAsState()
    val loadingState = mainViewModel.examLoadingState.collectAsState()
    val isInitialLoad = mainViewModel.isInitialLoad.collectAsState()


    // Initialize exam data when screen is first loaded
    LaunchedEffect(Unit) {
        Log.d("ExamsDebug", "Starting exam initialization")
        mainViewModel.compareExams()
    }

    // Get class number and define subjects based on class level
    val classNumber = when (student.value?.studentClass?.lowercase()) {
        "first" -> 1
        "second" -> 2
        "third" -> 3
        "fourth" -> 4
        "fifth" -> 5
        "sixth" -> 6
        "seventh" -> 7
        "eighth" -> 8
        "ninth" -> 9
        "tenth" -> 10
        else -> 0
    }
    Log.d("ExamsDebug", "Raw Class: ${student.value?.studentClass}")
    Log.d("ExamsDebug", "Mapped Class Number: $classNumber")
    Log.d("ExamsDebug", "Class Number: $classNumber")
    Log.d("ExamsDebug", "Loading State: ${loadingState.value}")
    Log.d("ExamsDebug", "Exam List Size: ${examList.value?.size}")
    Log.d("ExamsDebug", "Student Class: ${student.value?.studentClass}")
    val subjects = when (classNumber) {
        in 1..3 -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "علوم", "اجتماعيات"
        )
        in 4..8 -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "ثقافة مالية", "علوم",
            "تربية وطنية", "جغرافيا", "تاريخ"
        )
        in 9..10 -> listOf(
            "عربي", "انجليزي", "رياضة", "رياضيات",
            "تربية اسلامية", "تربية مهنية", "ثقافة مالية",
            "فيزياء", "احياء", "كيمياء", "علوم الأرض",
            "تربية وطنية", "جغرافيا", "تاريخ"
        )
        else -> emptyList()
    }

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            when (val currentState = loadingState.value) {
                is ExamLoadingState.Initial,
                is ExamLoadingState.Loading,
                is ExamLoadingState.Checking,
                is ExamLoadingState.CheckingNew,
                is ExamLoadingState.Fetching -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ExamLoadingState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currentState.message,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                else -> {
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
                                        text = "الامتحانات",
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
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                itemsIndexed(subjects) { index, subject ->
                                    SubjectCard(
                                        subject = subject,
                                        exams = examList.value?.filter {
                                            it.examTeacherSubject == subject
                                        } ?: emptyList(),
                                        onClick = {
                                            mainViewModel.updateBottomSheetState(index, true)
                                        },
                                        isLoading = examList.value == null || isInitialLoad.value
                                    )

                                    // Bottom Sheet for each subject
                                    if (examState.value.bottomSheet[index]) {
                                        ModalBottomSheet(
                                            containerColor = MaterialTheme.colorScheme.surface,
                                            onDismissRequest = {
                                                mainViewModel.updateBottomSheetState(index, false)
                                            }
                                        ) {
                                            ExamDetailSheet(
                                                subject = subject,
                                                exams = examList.value?.filter {
                                                    it.examTeacherSubject == subject
                                                } ?: emptyList()
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
    }
}

@Composable
private fun SubjectCard(
    subject: String,
    exams: List<com.example.schoolapp.datasource.local.entity.Exam>,
    onClick: () -> Unit,
    isLoading: Boolean = true
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Subject Icon
            Icon(
                painter = painterResource(
                    id = when (subject) {
                        "عربي" -> R.drawable.arabic
                        "انجليزي" -> R.drawable.english
                        "رياضة" -> R.drawable.sport
                        "رياضيات" -> R.drawable.math
                        "تربية اسلامية" -> R.drawable.baseline_broken
                        "تربية مهنية" -> R.drawable.baseline_broken
                        "علوم" -> R.drawable.science
                        "اجتماعيات" -> R.drawable.baseline_broken
                        "ثقافة مالية" -> R.drawable.baseline_broken
                        "تربية وطنية" -> R.drawable.baseline_broken
                        "جغرافيا" -> R.drawable.geography
                        "تاريخ" -> R.drawable.history
                        "فيزياء" -> R.drawable.baseline_broken
                        "احياء" -> R.drawable.baseline_broken
                        "كيمياء" -> R.drawable.baseline_broken
                        "علوم الأرض" -> R.drawable.baseline_broken
                        else -> R.drawable.baseline_broken
                    }
                ),
                contentDescription = subject,
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subject,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = if (exams.isNotEmpty()) "${exams.size} امتحان" else "لا توجد امتحانات",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun ExamDetailSheet(
    subject: String,
    exams: List<com.example.schoolapp.datasource.local.entity.Exam>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = subject,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (exams.isEmpty()) {
            Text(
                text = "لا توجد امتحانات مجدولة",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            exams.forEach { exam ->
                ExamItem(exam = exam)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
private fun ExamItem(exam: com.example.schoolapp.datasource.local.entity.Exam) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "التاريخ: ${exam.examDate}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = exam.examDay,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (!exam.examMaterial.isNullOrBlank()) {
            Text(
                text = "المواد المطلوبة: ${exam.examMaterial}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (!exam.examNotes.isNullOrBlank()) {
            Text(
                text = "ملاحظات: ${exam.examNotes}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}