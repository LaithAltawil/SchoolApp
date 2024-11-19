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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.schoolapp.Data.CalenderDays
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CalenderTopAppBar
import com.example.schoolapp.Presentation.Util.ExpandableButton
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//Calender page: Logic & UI                             =
//=======================================================
//solved @LT #simple || @(27:18)=="MainViewModel" variable name must start with small litter
//@LT: Done
//todo @MAS #qustion[not answered] || do you have any idea to fill this page?

//solved @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
//@LT: Done
@Composable
fun CalenderPage(mainViewModel: MainViewModel = MainViewModel()) {

    //solved @LT #qustion[not answered] || is this block used to call any suspend functions?
    //@LT:removed to to it being unnecessary


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
                    //solved @LT #qustion[not answered] || linking the topBar with CalenderTopAppBar.kt ?
                    //LT: Yes this is a function which contains the topappbar for this page
                    CalenderTopAppBar(
                        viewModel = mainViewModel,
                        modifier = Modifier,
                        title = "Calender"
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
                            ExpandableButton(name = item.day, text = item.event)
                                //solved @LT #qustion || what to do here?
                                //@LT:Removed to to it being unnecessary
                        }
                    }
                }
            }
        }
    }
}
@Composable
@Preview
fun CalenderPagePreview() {
    CalenderPage()
}