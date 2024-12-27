package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.CalenderDays
import com.example.schoolapp.Data.Counselorevents
import com.example.schoolapp.Presentation.Util.ExpandableButton
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.States.CalenderState

@Composable
fun Requests(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    //=======================================================
    //variables:local & states                              =
    //=======================================================
    val calendarItems = listOf(
        Counselorevents("Current Requests", "A huge Open Day for jobs in tech for the future", {}),
        Counselorevents(
            "submitted Requests",
            "A huge Open Day for jobs in medicine for the future",
            {}),
        Counselorevents("Done Requests", "A huge Open Day for jobs in finance for the future", {})
    )
    //=======================================================
    // logic & UI                                           =
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
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                            .height(180.dp)
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
                            Column(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                Text(
                                    text = "Counselor ", fontSize = 40.sp,
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    modifier = Modifier.padding(top = 40.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    text = " Requests", fontSize = 60.sp,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Visible,
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }


                        }
                    }
                    //LT: Yes this is a function which contains the TAB for this page

                },
                // Add content padding
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(calendarItems) { item ->
                            // ExpandableButton composable
                            ExpandableButton(
                                name = item.day,
                                text = item.event,
                                calenderState = CalenderState.EVENT_STATE,
                                viewModel = mainViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}