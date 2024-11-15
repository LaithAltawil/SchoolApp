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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Data.Exam
import com.example.schoolapp.Data.MockData.Mock.mockExamList
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ExamsMyTopAppBar
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

//=======================================================
//Exam Page: UI & logic                                 =
//=======================================================
//todo @LT #simple || @(37:69)=="MainViewModel" variable name must start with small litter
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamsPage(MainViewModel: MainViewModel = MainViewModel()) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    //todo @LT #simple || @(54:9)=="mainmenuitem" every second word must start with capital litter
    val state = MainViewModel.Examstate.collectAsStateWithLifecycle()


    val mainmenuitem = listOf(
        Subjects("Maths", painterResource(id = R.drawable.math)) { },
        Subjects("Science", painterResource(id = R.drawable.science)) { },
        Subjects("English", painterResource(id = R.drawable.english)) { },
        Subjects("History", painterResource(id = R.drawable.history)) {},
        Subjects("Arabic", painterResource(id = R.drawable.arabic)) {},
        Subjects("Computer Science", painterResource(id = R.drawable.science)) {},
        Subjects("Geography", painterResource(id = R.drawable.geography)) {}
    )

    //=======================================================
    //UI & logic                                            =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            //Main page UI: Scaffold
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                //todo @MAS #simple || please add the usage after answering the referred todo task
                topBar = {
                    ExamsMyTopAppBar(
                        viewModel = MainViewModel,
                        modifier = Modifier,
                        Title = "Exams"
                    )
                },
                // Add content padding
            ) { innerPadding ->
                //subjects
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    //lazy grid to hold the data logic & UI design
                    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                        /*here we will enter cards which will be the exams coming set in order from
                        left to right depending of its date
                        todo @LT #qustion[not answered] || if we can extend the size so we can
                         represent the data more clearly. Maybe you can set the row size in the grid to hold 2 cards*/
                        //todo @MAS #simple || please add the usage after answering the referred todo task
                        items(mainmenuitem.size) { item ->
                            //hold the subject details
                            Card(colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .wrapContentHeight()
                                    .size(200.dp)
                                    .width(100.dp)
                                    .clickable {
                                        MainViewModel.changeBottomSheetState()

                                    }
                            ) {
                                /*card UI: column todo @LT #medium ||
                                                    I think the column size is too small if you can
                                                    edit it so it can be wrapped with the whole card*/
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .wrapContentSize(Alignment.Center),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    //subject name
                                    Text(
                                        modifier = Modifier.padding(10.dp, top = 40.dp),
                                        text = mainmenuitem[item].name,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        overflow = TextOverflow.Ellipsis
                                    )


                                }

                            }
                            //todo @LT #simple || plz add this part logic
                            if (state.value.showBottomSheet) {
                                ModalBottomSheet(containerColor = MaterialTheme.colorScheme.primary,
                                    onDismissRequest = { MainViewModel.changeBottomSheetState() }
                                ) {
                                    // Bottom sheet content
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        //todo @LT #qustion[not answered] || what to do here?
                                        LazyColumn {
                                            items(mockExamList.size) { i ->
                                                ExamDetailsColumn(subject = mockExamList[i].subject,
                                                    date = mockExamList[i].date,
                                                    time = mockExamList[i].time,
                                                    location = mockExamList[i].location)




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

}
//Trial and error work to display
@Composable
fun ExamDetailsColumn( subject: String, date: String, time: String, location: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Subject: ${subject}", style = MaterialTheme.typography.displayMedium)
        Text("Date: ${date}", style = MaterialTheme.typography.displayMedium)
        Text("Time: ${time}", style = MaterialTheme.typography.displayMedium)
        Text("Location: ${location}", style = MaterialTheme.typography.displayMedium)
    }
}



//todo @LT #simple || put this in the relative file
@Composable
@Preview
fun ExamsPagePreview() {
    ExamsPage()
}