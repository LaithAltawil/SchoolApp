package com.example.schoolapp.Presentation.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Util.ProblemCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
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
    val sections = listOf(
        Triple(
            "طلبات جديدة",
            viewModel.getNewRequests(),
            MaterialTheme.colorScheme.primaryContainer
        ),
        Triple(
            "طلبات قيد المعالجة",
            viewModel.getActiveProblems(),
            MaterialTheme.colorScheme.secondaryContainer
        ),
        Triple(
            "طلبات تم حلها",
            viewModel.getSolvedProblems(),
            MaterialTheme.colorScheme.tertiaryContainer
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        if (sections.all { it.second.isEmpty() }) {
            item {
                Text(
                    text = "لا يوجد طلبات مسجلة",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            sections.forEach { (title, problems, color) ->
                if (problems.isNotEmpty()) {
                    item {
                        ExpandableSection(
                            title = title,
                            containerColor = color
                        ) {
                            Column {
                                problems.forEach { problem ->
                                    ProblemCard(problem = problem)
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpandableSection(
    title: String,
    containerColor: Color,
    content: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(true) }

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { isExpanded = !isExpanded },
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown
                    else Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (isExpanded) {
            content()
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
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is MainViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        DateSelectionBox(
            selectedDate = problemState.value.problemDate,
            onDateSelected = { date ->
                viewModel.updateProblemForm(problemDate = date.toString())
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = problemState.value.problemNotes,
            onValueChange = { viewModel.updateProblemForm(problemNotes = it) },
            label = { Text("تفاصيل المشكلة") },
            textStyle = LocalTextStyle.current.copy(textDirection = TextDirection.ContentOrRtl),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surface),
            maxLines = 10,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProblemDatePicker(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let {
                    val date = Instant.ofEpochMilli(it)
                        .atZone(ZoneOffset.UTC)
                        .toLocalDate()
                    onDateSelected(date)
                }
                onDismiss()
            }) {
                Text("تأكيد")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false
        )
    }
}


@Composable
private fun DateSelectionBox(
    selectedDate: String,
    onDateSelected: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            )
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (selectedDate.isNotEmpty()) {
                LocalDate.parse(selectedDate).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            } else "اختر تاريخ المشكلة",
            modifier = Modifier.padding(start = 16.dp)
        )

        IconButton(onClick = { showDatePicker = true }) {
            Icon(Icons.Default.DateRange, contentDescription = "اختر التاريخ")
        }
    }

    if (showDatePicker) {
        ProblemDatePicker(
            onDismiss = { showDatePicker = false },
            onDateSelected = { date ->
                onDateSelected(date)
                showDatePicker = false
            }
        )
    }
}