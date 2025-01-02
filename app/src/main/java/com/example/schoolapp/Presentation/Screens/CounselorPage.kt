package com.example.schoolapp.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Util.ProblemCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//=======================================================
//Counselor page: UI & logic                            =
//=======================================================
@Composable
fun ProblemPage(
    navController: NavController,
    viewModel: MainViewModel
) {
    val problemState = viewModel.problemPageState.collectAsState()
    val loadingState = viewModel.problemLoadingState.collectAsState()
    val context = LocalContext.current
    val options = listOf("مع الطلاب", "مع معلم", "مع الادارة", "مع الأهل", "أخرى")

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                topBar = {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                                .height(170.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {
                                IconButton(
                                    modifier = Modifier.padding(top = 50.dp, end = 5.dp),
                                    onClick = { navController.popBackStack() }
                                ) {
                                    Icon(
                                        Icons.Outlined.ArrowBack,
                                        contentDescription = "رجوع",
                                        modifier = Modifier.size(50.dp),
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Text(
                                    text = "المرشد",
                                    fontSize = 70.sp,
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    modifier = Modifier.padding(top = 40.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }

                        // Tab buttons row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            TabButton(
                                text = "المواعيد",
                                isSelected = problemState.value.currentTab == 0,
                                onClick = { viewModel.setCurrentProblemTab(0) }
                            )
                            TabButton(
                                text = "تقديم مشكلة",
                                isSelected = problemState.value.currentTab == 1,
                                onClick = { viewModel.setCurrentProblemTab(1) }
                            )
                        }
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    when (problemState.value.currentTab) {
                        0 -> AppointmentsTab(viewModel)
                        1 -> ProblemSubmissionTab(viewModel, options)
                    }
                }
            }

            if (problemState.value.showContactDialog) {
                //Unresolved reference: ContactDialog
                ContactDialog { viewModel.toggleContactDialog() }
            }
        }
    }
}

@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        )
    ) {
        Text(text = text)
    }
}

@Composable
private fun AppointmentsTab(viewModel: MainViewModel) {
    val problemState = viewModel.problemPageState.collectAsState()
    val activeProblems = viewModel.getActiveProblems()
    val resolvedProblems = viewModel.getResolvedProblems()

    // Add debug logging
    LaunchedEffect(Unit) {
        Log.d("ProblemDebug", "All problems: ${problemState.value.problems.size}")
        Log.d("ProblemDebug", "Active problems: ${activeProblems.size}")
        Log.d("ProblemDebug", "Resolved problems: ${resolvedProblems.size}")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        if (activeProblems.isEmpty() && resolvedProblems.isEmpty()) {
            item {
                Text(
                    text = "لا يوجد مشاكل مسجلة",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            if (activeProblems.isNotEmpty()) {
                item {
                    Text(
                        text = "المشاكل النشطة",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(activeProblems) { problem ->
                    ProblemCard(problem = problem)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if (resolvedProblems.isNotEmpty()) {
                item {
                    Text(
                        text = "المشاكل المحلولة",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(resolvedProblems) { problem ->
                    ProblemCard(problem = problem)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProblemSubmissionTab(
    viewModel: MainViewModel,
    options: List<String>
) {
    val problemState = viewModel.problemPageState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Problem Type Dropdown
        ExposedDropdownMenuBox(
            expanded = problemState.value.showTypeDropdown,
            onExpandedChange = { viewModel.toggleTypeDropdown() }
        ) {
            TextField(
                value = problemState.value.problemType,
                onValueChange = {},
                readOnly = true,
                label = { Text("نوع المشكلة") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = problemState.value.showTypeDropdown) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = problemState.value.showTypeDropdown,
                onDismissRequest = { viewModel.toggleTypeDropdown() }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            viewModel.updateProblemForm(problemType = option)
                            viewModel.toggleTypeDropdown()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Date Selection
        TextField(
            value = if (problemState.value.problemDate.isNotEmpty()) {
                LocalDate.parse(problemState.value.problemDate).format(
                    DateTimeFormatter.ofPattern("yyyy/MM/dd")
                )
            } else "اختر تاريخ",
            onValueChange = {},
            readOnly = true,
            label = { Text("تاريخ المشكلة") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        )

        // Date Picker Dialog
        if (showDatePicker) {
            ProblemDatePicker(
                onDismiss = { showDatePicker = false },
                onDateSelected = { date ->
                    viewModel.updateProblemForm(problemDate = date) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Details field
        TextField(
            value = problemState.value.problemNotes,
            onValueChange = { viewModel.updateProblemForm(problemNotes = it) },
            label = { Text("تفاصيل المشكلة") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            maxLines = 10
        )

        // Validation Error
        problemState.value.validationError?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.submitProblem() },
            modifier = Modifier.align(Alignment.End),
            enabled = !problemState.value.isLoading
        ) {
            Text(if (problemState.value.isLoading) "جاري التقديم..." else "تقديم المشكلة")
        }
    }
}

@Composable
fun ContactDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text("معلومات التواصل")
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text("المرشد المدرسي : جميل عبده")
                Text("ايميل : tdmer1994@dad.edu")
                Text("هاتف : 0799681787")
                Text("مكتب رقم 2019")
                Text("مواعيد المكتبية : من الساعة الثامنة صباحا الى الخامسة عصرا")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("اغلق")
            }
        }
    )
}

@Composable
fun ProblemDatePicker(
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit
) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "اختر تاريخ المشكلة",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { selectedDate = selectedDate.minusDays(1) },
                        enabled = selectedDate.isAfter(today.minusMonths(1))
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous Day"
                        )
                    }

                    Text(
                        text = selectedDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    IconButton(
                        onClick = { selectedDate = selectedDate.plusDays(1) },
                        enabled = selectedDate.isBefore(today)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next Day"
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
                    onDismiss()
                }
            ) {
                Text("تأكيد")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    )
}