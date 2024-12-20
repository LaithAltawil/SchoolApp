package com.example.schoolapp.Presentation.Util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R
import com.example.schoolapp.datasource.local.entity.Homework

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandableCard(homework: Homework, viewModel: MainViewModel, context: Context,onClick: () -> Unit) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    // states                                               =
    //=======================================================
    //var isExpanded by remember { mutableStateOf(false) }

    //=======================================================
    //Local variables                                      =
    //=======================================================
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.uploadFileToFirebase(uri) // Delegate upload to ViewModel
        } else {
            Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    //=======================================================
    //Logic & UI                                            =
    //=======================================================
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {

                   onClick()
                    //isExpanded = !isExpanded

            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = homework.homeworkTeacherSubject,
                    style = MaterialTheme.typography.headlineLarge
                )
                //Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(80.dp))
                //LT: this date will be replaced with the actual date when database is setup
                Text(homework.homeworkEndDay)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = homework.homeworkEndDate,
                    style = MaterialTheme.typography.labelLarge
                )
            }
//            if (isExpanded) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = homework.homeworkDetails,
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//                ) {
//                    Button(
//                        enabled = if (homework.homeworkIsComplete) false else true,
//                        onClick = {
//                            // Trigger file picker
//                            filePickerLauncher.launch(
//                                arrayOf(
//                                    "image/*",
//                                    "application/*"
//                                )
//                            )
//                        }, colors = ButtonDefaults.buttonColors(
//                            containerColor = MaterialTheme.colorScheme.onPrimary,
//                            contentColor = MaterialTheme.colorScheme.primary
//                        )
//                    ) {
//                        Text("Upload File")
//                    }
//                    Icon(
//                        modifier = Modifier.size(30.dp),
//                        painter =
//                        if (homework.homeworkIsComplete) {
//                            painterResource(id = R.drawable.baseline_check_circle_24)
//                        } else {
//                            painterResource(id = R.drawable.baseline_radio_button_unchecked_24)
//                        }, contentDescription = null
//                    )
//                }
//            }
        }
    }
}