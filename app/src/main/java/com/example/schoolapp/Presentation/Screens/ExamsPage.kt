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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Presentation.VM.ExamViewModel
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamsPage(MainViewModel: ExamViewModel = ExamViewModel()) {
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


    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp
                            )
                        ),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        title = {
                            Text(
                                "Exams",
                                fontSize = 50.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    modifier = Modifier.size(50.dp),
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Localized description",
                                    tint = MaterialTheme.colorScheme.background
                                )
                            }
                        },

                        )
                },
                // Add content padding
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                        //here we will enter cards which will be the exams coming set in order from left to right depending of its date

                        items(mainmenuitem.size) { item ->
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
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .wrapContentSize(Alignment.Center),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp, top = 40.dp),
                                        text = mainmenuitem[item].name,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Icon(
                                        painter = mainmenuitem[item].ImagePath,
                                        contentDescription = "",
                                        tint = Color.White
                                    )

                                }

                            }
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


@Composable
@Preview
fun ExamsPagePreview() {
    ExamsPage()
}