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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.online.model.HomeworkResponseModel
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalDate

@Composable
fun HomeworkCard(
    homework: Homework,
    response: HomeworkResponseModel?,
    viewModel: MainViewModel,
    context: Context
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showMarkAsCompletedDialog by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }

    val currentDate = LocalDate.now()
    val endDate = LocalDate.parse(homework.homeworkEndDate)
    val isOverdue = endDate.isBefore(currentDate)
    val isComplete = response?.isComplete == true

    val backgroundColor = when {
        isComplete -> MaterialTheme.colorScheme.tertiaryContainer
        isOverdue -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceContainer
    }

    val studentState = viewModel.student.collectAsState()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            isUploading = true
            val storageRef = FirebaseStorage.getInstance().reference
            val fileRef = storageRef.child("homework_submissions/${homework.homeworkId}_${studentState.value?.studentId}_${uri.lastPathSegment}")

            val uploadTask = fileRef.putFile(uri)
            uploadTask.addOnFailureListener { exception ->
                isUploading = false
                Toast.makeText(context, "فشل رفع الملف: ${exception.message}", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener { taskSnapshot ->
                fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    viewModel.submitHomeworkResponse(
                        homeworkId = homework.homeworkId,
                        studentId = studentState.value?.studentId ?: return@addOnSuccessListener,
                        filePath = downloadUri.toString(),
                        isComplete = true
                    )
                    isUploading = false
                    Toast.makeText(context, "تم رفع الملف بنجاح", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (showMarkAsCompletedDialog) {
        AlertDialog(
            onDismissRequest = { showMarkAsCompletedDialog = false },
            title = { Text("تأكيد تحديد الواجب كمنتهي") },
            text = { Text("هل أنت متأكد من أنك تريد تحديد هذا الواجب كمنتهي؟") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.submitHomeworkResponse(
                            homeworkId = homework.homeworkId,
                            studentId = studentState.value?.studentId ?: return@TextButton,
                            filePath = "تم تحديد الواجب كمنتهي",
                            isComplete = true
                        )
                        showMarkAsCompletedDialog = false
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                TextButton(onClick = { showMarkAsCompletedDialog = false }) {
                    Text("إلغاء")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when {
                        isComplete -> "منتهي"
                        isOverdue -> "متأخر"
                        else -> "جاري"
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = when {
                        isComplete -> MaterialTheme.colorScheme.onTertiaryContainer
                        isOverdue -> MaterialTheme.colorScheme.onErrorContainer
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                Text(
                    text = homework.homeworkTeacherSubject,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = homework.homeworkDetails,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "من: ${homework.homeworkStartDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "إلى: ${homework.homeworkEndDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (!isComplete && !isOverdue) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { showMarkAsCompletedDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("تحديد كمنتهي")
                        }

                        if (isUploading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        } else {
                            Button(
                                onClick = { filePickerLauncher.launch(arrayOf("*/*")) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary
                                )
                            ) {
                                Text("رفع ملف")
                            }
                        }
                    }
                }

                if (response?.isComplete == true) {
                    Text(
                        text = response.filePath.let { path ->
                            if (path.startsWith("http")) "تم رفع الملف" else path
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}