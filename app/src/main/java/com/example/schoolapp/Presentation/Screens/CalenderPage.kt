package com.example.schoolapp.Presentation.Screens

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
import com.example.compose.AppTheme
import com.example.schoolapp.Data.CalenderDays
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CalenderTopAppBar
import com.example.schoolapp.Presentation.Util.ExpandableButton
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//Calender page: Logic & UI                             =
//=======================================================
//todo @LT #simple || @(37:69)=="MainViewModel" variable name must start with small litter
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
@Composable
fun CalenderPage(MainViewModel: MainViewModel = MainViewModel()) {

    //todo @LT #qustion[not answered] || is this block used to call any suspend functions?
    LaunchedEffect(Unit) {
        MainViewModel.isTopappbarVisible6()
    }

    //=======================================================
    //variables:local & states                              =
    //=======================================================
    val calendarItems = listOf(
        CalenderDays("13/9", "A huge Open Day for jobs in tech for the future", {}),
        CalenderDays("14/9", "A huge Open Day for jobs in medicine for the future", {}),
        CalenderDays("15/9", "A huge Open Day for jobs in finance for the future", {}),
        CalenderDays("16/9", "A huge Open Day for jobs in teaching for the future", {}),
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
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    //todo @LT #qustion[not answered] || linking the topBar with CalenderTopAppBar.kt ?
                    CalenderTopAppBar(
                        viewModel = MainViewModel,
                        modifier = Modifier,
                        Title = "Calender"
                    )
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

                                //todo @LT #qustion[not answered] || what to do here?

                            }
                        }
                    }
                }
            }
        }
    }
}