package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MainMenuItem
import com.example.schoolapp.Data.MockData.Mock.HomeworkMock
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.Util.ExpandableCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R
import kotlinx.coroutines.launch

//=======================================================
//Main Page                                             =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(viewModel: MainViewModel, navController: NavController) {
    //=======================================================
    // initializing variables data                          =
    //=======================================================


    //=======================================================
    //variables: local & states                             =
    //=======================================================
    //states                                                =
    //=======================================================
    val studentState = viewModel.student.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val menuItems = listOf(
        MainMenuItem(
            title = "Calender",
            icon = painterResource(id = R.drawable.calendar),
            onClick = {
                navController.navigate(Screen.CalenderPage.route)
                scope.launch { drawerState.close() }
            }
        ),
        MainMenuItem(
            title = "Classes",
            icon = painterResource(id = R.drawable.training),
            onClick = {
                navController.navigate(Screen.ClassesPage.route)
                scope.launch { drawerState.close() }
            }
        ),
        MainMenuItem(
            title = "Exams",
            icon = painterResource(id = R.drawable.exam__1_),
            onClick = {
                navController.navigate(Screen.ExamsPage.route)
                scope.launch { drawerState.close() }
            }
        ),
        MainMenuItem(
            title = "Marks",
            icon = painterResource(id = R.drawable.grade),
            onClick = {
                navController.navigate(Screen.MarksPage.route)
                scope.launch { drawerState.close() }
            }
        ),
        MainMenuItem(
            title = "Homeworks",
            icon = painterResource(id = R.drawable.baseline_class_24),
            onClick = {
                navController.navigate(Screen.HomeworkPage.route)
                scope.launch { drawerState.close() }

            }
        ),
        MainMenuItem(
            title = "Settings",
            icon = painterResource(id = R.drawable.baseline_settings_24),
            onClick = {
                navController.navigate(Screen.SettingsPage.route)
                scope.launch { drawerState.close() }
            }
        ),
        MainMenuItem(
            title = "Logout",
            icon = painterResource(id = R.drawable.ic_logout),
            onClick = { /*TODO*/ }
        )
    )
    val item = listOf(
        "item1",
        "item2",
        "item3",
        "item4",
        "item5",
        "item6",
    )
    //=======================================================
    //this is the main page of the app which the student    =
    // enters after signing in                              =
    //=======================================================
    AppTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Surface(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topEnd = 25.dp,
                                topStart = 25.dp,
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp
                            )
                        )

                        .width(300.dp),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 90.dp)
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Screen.ProfilePage.route)
                                scope.launch { drawerState.close() }
                            }, modifier = Modifier
                                .padding(5.dp)
                                .width(800.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountBox,
                                    contentDescription = null, modifier = Modifier.size(50.dp)
                                )
                                if (drawerState.isOpen) {
                                    Text(
                                        text = "Account",
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                } else {
                                    Text(text = "", style = MaterialTheme.typography.displayMedium)
                                }
                            }
                        }
                        LazyColumn {
                            items(menuItems.size) { item ->
                                Card(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .width(600.dp)
                                        .height(70.dp)
                                        .clickable {
                                            menuItems[item].onClick()
                                        }, colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = menuItems[item].title,
                                                style = MaterialTheme.typography.headlineLarge
                                            )
                                            Icon(
                                                painter = menuItems[item].icon,
                                                contentDescription = null,
                                                modifier = Modifier.size(50.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            content = {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                                .height(170.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    modifier = Modifier.padding(top = 50.dp, start = 5.dp),
                                    onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                    Icon(
                                        Icons.Outlined.Menu,
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp),
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Welcome, Laith Ahmad Altawil" +
                                            //" ${studentState.value?.studentFirstName}" +
                                            "",
                                    style = TextStyle(fontSize = 36.sp),
                                    modifier = Modifier.padding(top = 58.dp),
                                    overflow = TextOverflow.Visible,
                                    color = MaterialTheme.colorScheme.onPrimary

                                )


                            }


                        }


                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LazyRow{

                                items(3){
                                    Card(
                                        modifier = Modifier
                                            .clip(
                                                RoundedCornerShape(
                                                    topEnd = 25.dp,
                                                    topStart = 25.dp,
                                                    bottomEnd = 25.dp,
                                                    bottomStart = 25.dp
                                                )
                                            )
                                            .padding(10.dp)
                                            .width(400.dp)
                                            .height(200.dp)
                                            .clickable {
                                            }, colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.training),
                                                contentDescription = null
                                            )

                                        }


                                    }



                                }

                            }


                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "To Do",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyColumn {
                                items(HomeworkMock.size) { index ->
                                    if (!HomeworkMock[index].isCompleted) {


                                        ExpandableCard(
                                            Data = HomeworkMock[index]
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}