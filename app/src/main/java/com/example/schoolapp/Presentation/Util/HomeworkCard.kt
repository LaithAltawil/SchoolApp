package com.example.schoolapp.Presentation.Util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.online.model.HomeworkResponseModel
import com.google.firebase.storage.FirebaseStorage

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@Composable
fun HomeworkCard(
    homework: Homework,
    response: HomeworkResponseModel?,
    viewModel: MainViewModel,
    context: Context
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }

    val studentState = viewModel.student.collectAsState()

    // Add the file picker launcher
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            isUploading = true
            val storageRef = FirebaseStorage.getInstance().reference
            val fileRef =
                storageRef.child("homework_submissions/${homework.homeworkId}_${studentState.value?.studentId}_${uri.lastPathSegment}")

            val uploadTask = fileRef.putFile(uri)
            uploadTask.addOnFailureListener { exception ->
                isUploading = false
                Toast.makeText(context, "Upload failed: ${exception.message}", Toast.LENGTH_LONG)
                    .show()
            }.addOnSuccessListener { taskSnapshot ->
                // Get the download URL
                fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    viewModel.submitHomeworkResponse(
                        homeworkId = homework.homeworkId,
                        studentId = studentState.value?.studentId ?: 0,
                        filePath = downloadUri.toString()
                    )
                    isUploading = false
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { exception ->
                    isUploading = false
                    Toast.makeText(
                        context,
                        "Failed to get download URL: ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Column {
                    Row {
                        Text(homework.homeworkStartDate)
                        Text("  من")
                    }
                    Row {
                        Text(homework.homeworkEndDate)
                        Text("  إلى")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = homework.homeworkTeacherSubject,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = homework.homeworkDetails,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(30.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        if (response?.isComplete == true) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.baseline_check_circle_24),
                                contentDescription = "Complete"
                            )

                            // Show submission date if available
                            response.submissionDate?.let { date ->
                                Text(
                                    text = "Submitted: $date",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        // Show upload button only if not completed
                        if (response?.isComplete != true) {
                            Button(
                                onClick = {
                                    filePickerLauncher.launch(arrayOf("*/*"))
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text("رفع ملف")
                            }
                        }
                    }
                }

                // Show file link if available
                if (!response?.filePath.isNullOrBlank()) {
                    Text(
                        text = "File uploaded",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}