package com.example.schoolapp.Presentation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//=======================================================
//Counselor page: UI & logic                            =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun CounselorPage(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = mainViewModel.Counselorstate.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }


    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    val context = LocalContext.current
    val options = listOf("with student", "with teacher", "with management", "with family", "other")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }


    //=======================================================
    //UI & page logic                                       =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                //TAB: UI & Logic
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
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp),
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "المرشد", fontSize = 70.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                modifier = Modifier.padding(top = 40.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                },
                // Add content padding
            ) { innerPadding ->
                //todo @MAS #simple || please add the usage after answering the referred todo task
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(modifier = Modifier
                                .padding(10.dp)
                                .width(150.dp), onClick = {
                                mainViewModel.toggleContactDialog()
                            }) {
                                Text(text = "للتواصل")
                            }
                            Button(modifier = Modifier
                                .padding(10.dp)
                                .width(150.dp), onClick = {
                                navController.navigate(Screen.RequestsPage.route)


                            }) {
                                Text(text = "مواعيد")
                            }
                        }
                        Divider(
                            modifier = Modifier
                                .padding(10.dp)
                                .height(3.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = !expanded }
                                ) {
                                    TextField(
                                        readOnly = true,
                                        value = selectedOptionText,
                                        onValueChange = { },
                                        label = { Text("Select an option") },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                        modifier = Modifier.clickable {
                                            expanded = !expanded
                                        }
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        options.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                text = { Text(selectionOption) },
                                                onClick = {
                                                    selectedOptionText = selectionOption
                                                    expanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                                Text(text = "نوع المشكلة")
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                //Problem with date picker
                                TextField(
                                    value = state.value.selectedDate.format(formatter),
                                    readOnly = true,
                                    onValueChange = {
                                        selectedDate = LocalDate.parse(it, formatter)

                                    },
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .clickable {
                                            android.app
                                                .DatePickerDialog(
                                                    context,
                                                    { _, year, month, dayOfMonth ->
                                                        selectedDate =
                                                            LocalDate.of(
                                                                year,
                                                                month + 1,
                                                                dayOfMonth
                                                            )
                                                    },
                                                    selectedDate.year,
                                                    selectedDate.monthValue - 1,
                                                    selectedDate.dayOfMonth
                                                )
                                                .show()
                                        })
                                Text(text = "تاريخ")
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextField(
                                    value = state.value.time,
                                    onValueChange = {
                                        mainViewModel.updateTime(it)
                                    },
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    maxLines = 10,
                                    label = { Text("Enter your text here") },
                                    textStyle = TextStyle(fontSize = 16.sp)
                                )
                                Text(text = "تفاصيل")
                            }

                            Button(modifier = Modifier
                                .padding(10.dp)
                                .width(150.dp), onClick = {
                                mainViewModel.submitRequest()
                            }) {
                                Text(text = if (state.value.isLoading) "يتم التسليم" else "حجز")
                            }

                        }




                    }
                }
                if (state.value.showContactDialog) {
                    ContactDialog(onDismiss = { mainViewModel.toggleContactDialog() })
                }

            }
        }
    }
}

@Composable
fun ContactDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End) { Text("معلومات التواصل") } },
        text = {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End) {
                Text(" المرشد المدرسي : جميل عبده")
                Text("ايميل : tdmer1994@dad.edu")
                Text("هاتف : 0799681787")
                Text("مكتب رقم 2019")
                Text("مواعيد المكتبية : من الساعة الثامنة صباحا الى الخامسة عصرا")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End){
                    Text(text ="اغلق")
                }
            }
        }
    )
}


