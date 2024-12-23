package com.example.schoolapp.Presentation.Util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schoolapp.Data.variable.opened
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.datasource.local.entity.Homework
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

//=======================================================
//Expandable card holds the logic for viewing it        =
//=======================================================
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandableCard(
    homework: Homework,
    viewModel: MainViewModel,
    context: Context,
    onClick: () -> Unit
) {
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
    //handle the time format
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateString(dateFromApi: String): String {
        // Define the input date format
        val inputFormatter =
            DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)

        // Parse the input date string to a LocalDateTime
        val parsedDate = LocalDateTime.parse(dateFromApi, inputFormatter)

        // Define the output date format
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Format the parsed date to the desired format
        return parsedDate.format(outputFormatter)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                opened=homework.homeworkId
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = homework.homeworkTeacherSubject,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                Text("Submit by")
                Spacer(modifier = Modifier.width(8.dp))
                Card(modifier = Modifier.height(50.dp).width(100.dp)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally
                        ,verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                        modifier =Modifier.fillMaxSize() ) {
                        Text(homework.homeworkEndDay)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = convertDateString(homework.homeworkEndDate),
                            style = MaterialTheme.typography.labelLarge
                        )

                    }
                }

            }
        }
    }
}

