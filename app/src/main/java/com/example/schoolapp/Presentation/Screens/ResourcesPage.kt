package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ResourcesTopAppBar
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//todo @LT #simple || explain this fun logic here       =
//=======================================================
//todo @LT #simple || @(44:19)=="MainViewModel" variable name must start with small litter
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesPage(MainViewModel: MainViewModel = MainViewModel()) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = MainViewModel.Resourcesstate.collectAsStateWithLifecycle()

    //create A state for the Resources page and add it to the main view model
    LaunchedEffect(Unit) {
        MainViewModel.isTopappbarVisible4()
    }

    //=======================================================
    //Logic & UI,todo @MAS @LT #simple || add usage        =
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
                    ResourcesTopAppBar(
                        viewModel =
                        MainViewModel,
                        modifier = Modifier,
                        Title = "Resources"
                    )
                },
                // Add content padding
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    {
                        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                            //here we will enter cards which will be the exams coming set in order from left to right depending of its date
                            items(state.value.Subjects.size) { item ->
                                Card(colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .wrapContentHeight()
                                        .size(200.dp)
                                        .width(100.dp)
                                        .clickable {
                                            MainViewModel.changeBottomSheetState2(item)
                                        }
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .wrapContentSize(Alignment.Center),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(10.dp, top = 40.dp),
                                            text = state.value.Subjects[item].name,
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                if (state.value.showBottomSheet[item]) {
                                    ModalBottomSheet(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        onDismissRequest = {
                                            MainViewModel.changeBottomSheetState(item)
                                        }
                                    ) {
                                        // Bottom sheet content
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            Text(
                                                text = state.value.Subjects[item].name,
                                                fontSize = 30.sp
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