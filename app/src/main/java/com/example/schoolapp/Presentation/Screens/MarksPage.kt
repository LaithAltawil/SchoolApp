package com.example.schoolapp.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.schoolapp.Presentation.VM.States.MarksLoadingState
import com.example.schoolapp.R
import com.example.schoolapp.datasource.local.entity.Mark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksPage(mainViewModel: MainViewModel, navController: NavController) {
    val marksState = mainViewModel.Marksstate.collectAsStateWithLifecycle()
    val student = mainViewModel.student.collectAsState()
    val marksList = mainViewModel.marksList.collectAsState()
    val loadingState = mainViewModel.marksLoadingState.collectAsState()

    // Initialize marks data when screen is first loaded
    LaunchedEffect(Unit) {
        if (student.value != null) {
            Log.d("MarksPage", "Student ID for marks: ${student.value?.studentId}")
            mainViewModel.checkMarks()
        } else {
            Log.e("MarksPage", "Student is null in MarksPage")
        }
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
                is MarksLoadingState.Initial,
                is MarksLoadingState.Loading,
                is MarksLoadingState.Checking -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is MarksLoadingState.Error -> {
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
                                    .clip(
                                        RoundedCornerShape(
                                            bottomEnd = 16.dp,
                                            bottomStart = 16.dp
                                        )
                                    )
                                    .height(170.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                ) {
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
                                        text = "العلامات",
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
                                    SubjectMarkCard(
                                        subject = subject,
                                        marks = marksList.value?.filter {
                                            it.markTeacherSubject == subject
                                        } ?: emptyList(),
                                        onClick = {
                                            mainViewModel.updateBottomSheetState1(index, true)
                                        }
                                    )

                                    // Safe access to BottomSheet state
                                    if (index < marksState.value.bottomSheet.size && marksState.value.bottomSheet[index]) {
                                        ModalBottomSheet(
                                            containerColor = MaterialTheme.colorScheme.surface,
                                            onDismissRequest = {
                                                mainViewModel.updateBottomSheetState1(index, false)
                                            }
                                        ) {
                                            MarkDetailSheet(
                                                subject = subject,
                                                marks = marksList.value?.filter {
                                                    it.markTeacherSubject == subject
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
private fun SubjectMarkCard(
    subject: String,
    marks: List<Mark>,
    onClick: () -> Unit
) {
    // Create default mark if list is empty
    val mark = marks.firstOrNull() ?: Mark.empty(0, subject)

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
            Icon(
                painter = painterResource(
                    id = when (subject) {
                        "عربي" -> R.drawable.arabic
                        "انجليزي" -> R.drawable.english
                        "رياضة" -> R.drawable.sports
                        "رياضيات" -> R.drawable.math
                        "تربية اسلامية" -> R.drawable.quran
                        "تربية مهنية" -> R.drawable.worker
                        "علوم" -> R.drawable.science
                        "اجتماعيات" -> R.drawable.social
                        "ثقافة مالية" -> R.drawable.finance
                        "تربية وطنية" -> R.drawable.jordan
                        "جغرافيا" -> R.drawable.geography
                        "تاريخ" -> R.drawable.history
                        "فيزياء" -> R.drawable.physics
                        "احياء" -> R.drawable.biology
                        "كيمياء" -> R.drawable.chem
                        "علوم الأرض" -> R.drawable.pickaxe
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

            Text(
                text = "المجموع: ${mark.totalMark.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun MarkDetailSheet(
    subject: String,
    marks: List<Mark>
) {
    // Create default mark if list is empty
    val mark = marks.firstOrNull() ?: Mark.empty(0, subject)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = subject,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        MarkItem(mark = mark)
    }
}

@Composable
private fun MarkItem(mark: Mark) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                MarkRow("الامتحان الأول", mark.firstMark)
                MarkRow("الامتحان الثاني", mark.secondMark)
                MarkRow("الامتحان الثالث", mark.thirdMark)
                MarkRow("الامتحان الرابع", mark.forthMark)
                Divider(modifier = Modifier.padding(vertical = 4.dp))
                MarkRow("المجموع", mark.totalMark, isTotal = true)
            }
        }
    }
}

@Composable
private fun MarkRow(label: String, value: Float, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            // Convert float to int to remove decimal places when the value is whole
            text = value.toInt().toString(),
            style = if (isTotal) MaterialTheme.typography.titleMedium
            else MaterialTheme.typography.bodyLarge,
            color = if (isTotal) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium
            else MaterialTheme.typography.bodyLarge
        )
    }
}