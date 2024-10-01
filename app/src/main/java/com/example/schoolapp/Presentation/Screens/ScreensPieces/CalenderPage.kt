package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.compose.AppTheme
import com.example.schoolapp.Data.CalenderDays
import com.example.schoolapp.Presentation.Util.ExpandableButton
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

@Composable
fun CalenderPage(MainViewModel: MainViewModel=MainViewModel()){

    LaunchedEffect(Unit) {
        MainViewModel.isTopappbarVisible()
    }
    val calendarItems = listOf(
        CalenderDays("13/9","A huge Open Day for jobs in tech for the future",{}),
        CalenderDays("14/9","A huge Open Day for jobs in medicine for the future",{}),
        CalenderDays("15/9","A huge Open Day for jobs in finance for the future",{}),
        CalenderDays("16/9","A huge Open Day for jobs in teaching for the future",{}),
    )


    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    MyTopAppBar(viewModel = MainViewModel, modifier = Modifier, Title = "Calender")
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
                            ExpandableButton(name = item.day, Text = item.event) {

                            }

                        }
                    }


                }
            }


        }


    }



}