package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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


@Composable
fun HomeworkPage(HomeworkPageState: MainViewModel = MainViewModel()) {
    val state = HomeworkPageState.state.collectAsStateWithLifecycle()


    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {

                    HomeworksTopAppBar(viewModel = HomeworkPageState,
                        modifier = Modifier,
                        Title ="HomeWork")
                },
                // Add content padding
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    LazyColumn {
                        items(HomeworkMock.size) { index ->
                            ExpandableCard(
                                Data = HomeworkMock[index],
                                viewmodel = HomeworkPageState
                            )
                        }


                    }


                }
            }


        }

    }
}

@Preview
@Composable
fun HomeworkPagePreview() {
    HomeworkPage()
}


