package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.HomeworkMock
import com.example.schoolapp.Presentation.Screens.ScreensPieces.HomeworksTopAppBar
import com.example.schoolapp.Presentation.Util.ExpandableCard
import com.example.schoolapp.Presentation.VM.MainViewModel
//=======================================================
//home work page: UI & logic                            =
//=======================================================
@Composable
fun HomeworkPage(homeWorkPageState: MainViewModel= MainViewModel()) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = homeWorkPageState.state.collectAsStateWithLifecycle()

    //=======================================================
    //UI & Logic                                            =
    //=======================================================
    AppTheme{
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            //main page UI:scaffold
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                //TAB: UI & Logic
                topBar = {
                    HomeworksTopAppBar(
                        viewModel = homeWorkPageState,
                        modifier = Modifier,
                        title = "HomeWork"
                    )
                },
                // Add content padding
            ) { innerPadding ->
                //main homeWork UI: column
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    //lazy column to hold the data logic & UI design
                    LazyColumn {
                        //controls the lazy size
                        items(HomeworkMock.size) { index ->
                            //main homeWork card UI & Logic
                            //todo @LT #medium|| please add more details like this:-
                            /*
                            * +---------------------------+
                            * |subject name     due date* |
                            * +---------------------------+
                            * due date can be the general format or day counter example: 2 day to submit       */
                            ExpandableCard(
                                Data = HomeworkMock[index],
                                viewmodel = homeWorkPageState
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
@Preview
fun HomeworkPagePreview() {
    HomeworkPage()
}