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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MainMenuItem
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.Screens.ScreensPieces.MainCards
import com.example.schoolapp.Presentation.Util.ExpandableCard
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.States.HomeworkLoadingState
import com.example.schoolapp.R
import kotlinx.coroutines.launch

//=======================================================
//Main Page                                             =
//=======================================================
@Composable
fun MainMenu(viewModel: MainViewModel, navController: NavController) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    //states                                                =
    //=======================================================
    val homeworkListState = viewModel.homeworkList.collectAsState()
    val studentState = viewModel.student.collectAsState()
    val loadingState = viewModel.loadingState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //=======================================================
    //local variables                                       =
    //=======================================================
    val item = listOf(
        "item1",
        "item2",
        "item3",
        "item4",
        "item5",
        "item6",
    )
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
            title = "Counsellor",
            icon = painterResource(id = R.drawable.help),
            onClick = {
                navController.navigate(Screen.CounselorPage.route)
            }),
        MainMenuItem(
            title = "Logout",
            icon = painterResource(id = R.drawable.ic_logout),
            onClick = { /*TODO*/ }
        )
    )
    val menuCard=listOf(
        MainCards(),
        MainCards(),
        MainCards()
    )
    val studentFirstName = studentState.value?.studentFirstName
    val studentSecondName = studentState.value?.studentSecondName
    val studentThirdName = studentState.value?.studentThirdName
    val studentName = "$studentFirstName $studentSecondName $studentThirdName"
    //=======================================================
    //this is the main page of the app which the student    =
    // enters after signing in                              =
    //=======================================================
    LaunchedEffect(Unit) {
        //setting todo_list with homeworks
        viewModel.checkHomework()
    }
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                                .height(170.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                IconButton(
                                    modifier = Modifier.padding(top = 50.dp),
                                    onClick = { scope.launch { drawerState.open() } }
                                ) {
                                    Icon(
                                        Icons.Outlined.Menu,
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp),
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Welcome, $studentFirstName",
                                    style = TextStyle(fontSize = 36.sp),
                                    modifier = Modifier.padding(top = 58.dp),
                                    overflow = TextOverflow.Visible,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        // Quick Access Cards in horizontal scroll
                        LazyRow(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(menuCard.size) {
                                MainCards()
                            }
                        }
                        // To Do Section Title
                        Text(
                            text = "To Do",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                        // Homework List
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        ) {
                            //handle different loading states
                            when (loadingState.value) {
                                //=======================================================
                                //Initial state                                         =
                                //=======================================================
                                HomeworkLoadingState.Initial -> {
                                    item {
                                        Text(
                                            "No homework available",
                                            modifier = Modifier.padding(16.dp),
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                //=======================================================
                                //Loading states                                        =
                                //=======================================================
                                //checking homework state
                                HomeworkLoadingState.CheckingHomework -> {
                                    item {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(50.dp),
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text(
                                                "Checking homework...",
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                                //fetching homework state
                                HomeworkLoadingState.FetchingHomework -> {
                                    item {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(50.dp),
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text(
                                                "Getting homework...",
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                                //checking new homework state
                                HomeworkLoadingState.CheckingNewHomework -> {
                                    item {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(50.dp),
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text(
                                                "Checking new homework...",
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                                //=======================================================
                                //Completed state                                       =
                                //=======================================================
                                HomeworkLoadingState.Completed -> {
                                    if (homeworkListState.value != null) {
                                        items(homeworkListState.value!!.size) { index ->
                                            val isComplete =
                                                homeworkListState.value!![index].homeworkIsComplete
                                                    ?: false
                                            if (!isComplete) {
                                                ExpandableCard(
                                                    homeworkListState.value!![index],
                                                    viewModel,
                                                    LocalContext.current,
                                                ) {
                                                    navController.navigate(Screen.HomeworkPage.route)
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
        )
    }
}