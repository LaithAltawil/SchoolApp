package com.example.schoolapp.Presentation.Screens

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.HomeworkMock
import com.example.schoolapp.Data.homework
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkPage() {

    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                bottomEnd = 20.dp, bottomStart = 20.dp

                            )
                        ),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        title = {
                            Text(
                                "Homeworks",
                                fontSize = 50.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    modifier = Modifier.size(50.dp),
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Localized description",
                                    tint = MaterialTheme.colorScheme.background
                                )
                            }
                        },

                        )
                },
                // Add content padding
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(HomeworkMock.size) { index ->
                            ExpandableCard(Data = HomeworkMock[index])
                        }


                    }


                }
            }


        }

    }
}

@Preview
@Composable
fun HomeworkPagePreview() {
    HomeworkPage()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandableCard(Data: homework) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data // The selected file's URI
            // Handle the URI and save the file to the database
        }
    }
    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = Data.title,
                style = MaterialTheme.typography.headlineSmall
            )
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = Data.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "*/*" // Allow all file types
                        }
                        launcher.launch(intent)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Upload File")
                }

            }
        }
    }
}