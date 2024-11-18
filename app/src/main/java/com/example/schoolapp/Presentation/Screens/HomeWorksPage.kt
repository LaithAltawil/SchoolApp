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
//solved @LT #simple || @(26:18)=="HomeworkPageState" variable name must start with small litter

//solved @LT #medium~#hard || try adding the @preview notation to be able to use the design tab

@Composable
fun HomeworkPage(homeworkpagestate: MainViewModel= MainViewModel()) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = homeworkpagestate.state.collectAsStateWithLifecycle()

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
                //todo @MAS #simple || please add the usage after answering the referred todo task
                topBar = {
                    HomeworksTopAppBar(
                        viewModel = homeworkpagestate,
                        modifier = Modifier,
                        Title = "HomeWork"
                    )
                },
                // Add content padding
            ) { innerPadding ->
                //todo @MAS #simple || please add the usage after answering the referred todo task
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(HomeworkMock.size) { index ->
                            ExpandableCard(
                                Data = HomeworkMock[index],
                                viewmodel = homeworkpagestate
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