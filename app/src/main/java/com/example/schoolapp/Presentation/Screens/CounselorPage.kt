package com.example.schoolapp.Presentation.Screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.Util.CounselorCard
import com.example.schoolapp.Presentation.Util.DatePickerModal
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R


//=======================================================
//Counselor page: UI & logic                            =
//=======================================================
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CounselorPage(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = mainViewModel.state.collectAsStateWithLifecycle()

    //=======================================================
    //UI & page logic                                       =
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
                //TAB: UI & Logic
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                            .height(170.dp)
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
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "Counselor", fontSize = 70.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                modifier = Modifier.padding(top = 40.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                },
                // Add content padding
            ) { innerPadding ->
                //todo @MAS #simple || please add the usage after answering the referred todo task
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyRow {
                            items(3) {
                                Card(
                                    modifier = Modifier
                                        .width(420.dp)
                                        .height(230.dp)
                                        .padding(10.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Date here")
                                        Text("Status here")

                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Last meeting feedback:-")
                                        Text(
                                            text = "Today, we learned alot about learniung alot Today," +
                                                    " we learned alot about learniung alot Today," +
                                                    " we learned alot about learniung alot" +
                                                    "Today, we learned alot about learniung alot" +
                                                    "Today, we learned alot about learniung alot"
                                        )


                                    }
                                }

                            }
                        }
                        Divider(modifier = Modifier.padding(10.dp).height(3.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(modifier = Modifier.padding(10.dp).width(150.dp), onClick = {}) {
                                Text(text = "Add Meeting")
                            }
                            Button(modifier = Modifier.padding(10.dp).width(150.dp), onClick = {}) {
                                Text(text = "Add Feedback")
                            }

                        }
                        Divider(modifier = Modifier.padding(10.dp).height(3.dp))
                        CounselorCard()




                    }


                }
            }
            //data picker logic

        }
    }
}
