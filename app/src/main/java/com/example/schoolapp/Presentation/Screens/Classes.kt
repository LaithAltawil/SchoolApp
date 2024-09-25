package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock
import com.example.schoolapp.Data.MockData.Mock.classList
import com.example.schoolapp.Data.MockData.Mock.daysOfWeek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentClass() {
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp
                            )
                        ),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        title = {
                            Text(
                                "Classes",
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
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(daysOfWeek.size) {
                                Column {

                                    Text(
                                        text = daysOfWeek[it],
                                        fontSize = 30.sp,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    LazyRow {
                                        items(classList.size) {
                                            Card(
                                                modifier = Modifier
                                                    .padding(16.dp)
                                                    .size(150.dp),
                                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                                            ) {
                                                Column(modifier = Modifier.fillMaxSize().padding(16.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                                                    ) {
                                                    Text(
                                                        text = classList[it].subjectName,
                                                        fontSize = 20.sp,
                                                        color = MaterialTheme.colorScheme.secondary
                                                    )
                                                    Spacer(modifier = Modifier.height(10.dp))
                                                    Text(
                                                        text =classList[it].teacher,
                                                        fontSize = 20.sp,
                                                        color = MaterialTheme.colorScheme.secondary
                                                    )
                                                    Spacer(modifier = Modifier.height(10.dp))
                                                    Text(
                                                        text = classList[it].time,
                                                        fontSize = 20.sp,
                                                        color = MaterialTheme.colorScheme.secondary
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


    }
}

@Composable
@Preview
fun StudentClassPreview() {
    StudentClass()
}