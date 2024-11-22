package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var selectedItemIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()


    //=======================================================
    // Logic & UI                                           =
    //=======================================================
    LaunchedEffect(selectedItemIndex) {
        lazyListState.scrollToItem(selectedItemIndex)
    }
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
                        //child column of the lazyColumn

                        //represent the day name
                        //underway @LT #meduim || please consider changing the design here with @MAS
                        //LT:please if you have any idea please provide because i am out of them :(
                        /*MAS: you may do it like the whole day for the whole page
                        * with buttons to switch
                        * check session.png*/
                        //Currently under work, currently did a day and will add a button to switch days
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Text(
                                text = daysOfWeek[selectedItemIndex],
                                fontSize = 24.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                            ) {
                                Button(onClick = {
                                    selectedItemIndex =
                                        (selectedItemIndex - 1).coerceAtLeast(0)
                                }) {
                                    Text("Previous")
                                }
                                Button(onClick = {
                                    selectedItemIndex =
                                        (selectedItemIndex + 1).coerceAtMost(classList.lastIndex)
                                }) {
                                    Text("Next")
                                }
                            }
                            LazyRow(state = lazyListState, modifier = Modifier.fillMaxWidth()) {
                                items(classList) { item ->
                                    Card(modifier = Modifier.size(340.dp).fillMaxWidth(),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ),

                                        ){
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = item.subjectName,
                                                fontSize = 24.sp,
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = item.time,
                                                fontSize = 18.sp,
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = item.teacher,
                                                fontSize = 18.sp,
                                                modifier = Modifier.padding(bottom = 8.dp))

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