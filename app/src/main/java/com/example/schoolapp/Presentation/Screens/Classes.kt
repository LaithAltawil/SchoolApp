package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.ClassesList
import com.example.schoolapp.Data.MockData.Mock.daysOfWeek
import com.example.schoolapp.Data.classInfo
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//sessions table: Logic & UI                            =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StudentClass(mainViewModel: MainViewModel ,
//                 navController: NavController) {
//    //=======================================================
//    //variables: local & stats                              =
//    //=======================================================
//    val state by mainViewModel.Classesstate.collectAsState()
//    var selectedItemIndex by remember { mutableStateOf(0) }
//    val lazyListState = rememberLazyListState()
//    //=======================================================
//    // Logic & UI                                           =
//    //=======================================================
//    LaunchedEffect(selectedItemIndex) {
//        lazyListState.scrollToItem(selectedItemIndex)
//    }
//    AppTheme {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize(),
//            color = MaterialTheme.colorScheme.primaryContainer
//        ) {
//            Scaffold(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                //TAB main UI & Logic
//                topBar = {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
//                            .height(170.dp)
//                            .background(MaterialTheme.colorScheme.primary)
//                    ) {
//                        Row(
//                            modifier = Modifier.fillMaxSize(),
//
//                            ) {
//                            IconButton(
//                                modifier = Modifier.padding(top = 50.dp, start = 5.dp),
//                                onClick = {
//                                    navController?.popBackStack()
//                                }
//                            ) {
//                                Icon(
//                                    Icons.Outlined.ArrowBack,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(50.dp),
//                                    tint = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "Classes", fontSize = 70.sp,
//                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
//                                modifier = Modifier.padding(top=40.dp),
//                                color = MaterialTheme.colorScheme.onPrimary
//                            )
//                        }
//                    }
//
//                },
//                // Add content padding
//            ) {
//                //Main page UI: column
//                Column(
//                    modifier = Modifier
//                        .padding(it)
//                        .fillMaxSize()
//                ) {
//                    //Main column
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        //child column of the lazyColumn
//
//                        //represent the day name
//                        //underway @LT #meduim || please consider changing the design here with @MAS
//                        //LT:please if you have any idea please provide because i am out of them :(
//                        /*MAS: you may do it like the whole day for the whole page
//                        * with buttons to switch
//                        * check session.png*/
//                        //Currently under work, currently did a day and will add a button to switch days
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(start = 10.dp, end = 10.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//
//                            ) {
//                            Text(
//                                text = daysOfWeek[selectedItemIndex],
//                                fontSize = 24.sp,
//                                modifier = Modifier.padding(bottom = 8.dp)
//                            )
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp),
//                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
//                            ) {
//                                Button(onClick = {
//                                    selectedItemIndex =
//                                        (selectedItemIndex - 1).coerceAtLeast(0)
//                                }, modifier = Modifier.width(150.dp).height(40.dp)
//                                    ) {
//                                    Text("Previous")
//                                }
//                                Button(onClick = {
//                                    selectedItemIndex =
//                                        (selectedItemIndex + 1).coerceAtMost(classList.lastIndex+1)
//                                },
//                                    modifier = Modifier.width(150.dp).height(40.dp),
//
//                                        ) {
//                                    Text("Next")
//                                }
//                            }
//                            Column(
//                                modifier = Modifier
//                                    .clip(RoundedCornerShape(26.dp))
//                                    .background(MaterialTheme.colorScheme.primary)
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//
//                            ) {
//                                LazyColumn(state = lazyListState, modifier = Modifier
//                                    .fillMaxWidth()) {
//
//                                        items(ClassesList) { List -> // Iterate through outer list
//                                            Column(
//                                                modifier = Modifier
//
//                                                    .fillMaxSize()
//                                                    .padding(16.dp)
//                                                ,
//                                                horizontalAlignment = Alignment.CenterHorizontally
//                                            ) {
//
//                                                Text(
//                                                    text = List[selectedItemIndex].subjectName,
//                                                    fontSize = 26.sp,
//                                                    modifier = Modifier.padding(bottom = 8.dp),
//                                                    color = MaterialTheme.colorScheme.onPrimary
//                                                )
//                                                Spacer(modifier = Modifier.height(8.dp))
//                                                Text(
//                                                    text = List[selectedItemIndex].teacher,
//                                                    fontSize = 22.sp,
//                                                    modifier = Modifier.padding(bottom = 8.dp)
//                                                    ,
//                                                    color = MaterialTheme.colorScheme.onPrimary
//
//                                                )
//                                                Spacer(modifier = Modifier.height(8.dp))
//                                                Text(
//                                                    text = List[selectedItemIndex].time,
//                                                    fontSize = 18.sp,
//                                                    modifier = Modifier.padding(bottom = 8.dp),
//                                                    color = MaterialTheme.colorScheme.onPrimary
//
//                                                )
//
//
//                                            }
//
//
//
//                                        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                                }
//
//                            }
//
//
//
//
//                        }
//                    }
//
//                }
//
//
//            }
//        }
//    }
//}
//new version work on this pls
@Composable
fun StudentClass(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val state by mainViewModel.Classesstate.collectAsState()
    var selectedDayIndex by remember { mutableStateOf(0) }

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            ClassesScreen(
                selectedDayIndex = selectedDayIndex,
                onDayChanged = { selectedDayIndex = it },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun ClassesScreen(
    selectedDayIndex: Int,
    onDayChanged: (Int) -> Unit,
    onBackClick: () -> Unit,

    ) {
    Scaffold(
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
                        onClick = { onBackClick() }
                    ) {
                        Icon(
                            Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(50.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Calender",
                        fontSize = 70.sp,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        modifier = Modifier.padding(top = 40.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            DaySelector(
                selectedDayIndex = selectedDayIndex,
                onDayChanged = onDayChanged,
                maxDayIndex = ClassesList.size - 1  // Pass the maximum valid index
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add null check and bounds check
            if (selectedDayIndex in ClassesList.indices) {
                ClassesList(selectedDayIndex = selectedDayIndex)
            }
        }
    }
}

@Composable
private fun ClassesTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Classes",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun DaySelector(
    selectedDayIndex: Int,
    onDayChanged: (Int) -> Unit,
    maxDayIndex: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = daysOfWeek.getOrElse(selectedDayIndex) { "Unknown Day" },
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onDayChanged(selectedDayIndex - 1) },
                enabled = selectedDayIndex > 0,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Previous Day")
            }

            Button(
                onClick = { onDayChanged(selectedDayIndex + 1) },
                enabled = selectedDayIndex < maxDayIndex,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("Next Day")
            }
        }
    }
}

@Composable
private fun ClassesList(selectedDayIndex: Int) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp)),
        color = MaterialTheme.colorScheme.primary
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ClassesList) { classList ->
                ClassCard(classInfo = classList[selectedDayIndex])
            }
        }
    }
}

@Composable
private fun ClassCard(classInfo: classInfo) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = classInfo.subjectName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = classInfo.teacher,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = classInfo.time,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

//
//@Composable
//@Preview
//fun StudentClassPreview() {
//    StudentClass()
//}