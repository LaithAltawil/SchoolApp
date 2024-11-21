package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.classList
import com.example.schoolapp.Data.MockData.Mock.daysOfWeek
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ClassesTopAppBar
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//sessions table: Logic & UI                            =
//=======================================================
@Composable
fun StudentClass(mainViewModel: MainViewModel = MainViewModel()) {
    //=======================================================
    //variables: local & stats                              =
    //=======================================================
    val state by mainViewModel.Classesstate.collectAsState()

    //=======================================================
    // Logic & UI                                           =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
                color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                //TAB main UI & Logic
                topBar = {
                    ClassesTopAppBar(
                        viewModel = mainViewModel,
                        modifier = Modifier,
                        title = "Classes"
                    )
                },
                // Add content padding
            ) {
                //Main page UI: column
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    //Main column
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Main lazy column
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            //MAS: is it working on sequence 0,1,2,,,,,n?
                            //LT: yes 0,1,2 because it is using an array
                            items(daysOfWeek.size) {
                                //child column of the lazyColumn
                                Column {
                                    //represent the day name
                                    Text(
                                        text = daysOfWeek[it],
                                        fontSize = 30.sp,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    //child lazy row
                                    LazyRow {
                                        //decide the lazy row size
                                        items(classList.size) {
                                            //Card UI to hold the session data
                                            Card(
                                                modifier = Modifier
                                                    .padding(16.dp)
                                                    .size(150.dp),
                                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                                            ) {
                                                //todo @LT #meduim || please consider changing the design here with @MAS
                                                //LT:please if you have any idea please provide because i am out of them :(
                                                /*MAS: you may do it like the whole day for the whole page
                                                * with buttons to switch
                                                * check session.png*/
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(16.dp),
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
                                                        text = classList[it].teacher,
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