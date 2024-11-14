package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MainMenuItem
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.R
import kotlinx.coroutines.launch

//=======================================================
//todo @LT #simple || explain this fun logic here       =
//=======================================================
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(navController: NavController) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val menuItems = listOf(
        MainMenuItem(
            title = "Calender",
            icon = painterResource(id = R.drawable.calendar),
            onClick = {
                navController.navigate(Screen.CalenderPage.route)
            }
        ),
        MainMenuItem(
            title = "Classes",
            icon = painterResource(id = R.drawable.training),
            onClick = {
                navController.navigate(Screen.ClassesPage.route)
            }
        ),
        MainMenuItem(
            title = "Exams",
            icon = painterResource(id = R.drawable.exam__1_),
            onClick = {
                navController.navigate(Screen.ExamsPage.route)
            }
        ),
        MainMenuItem(
            title = "Marks",
            icon = painterResource(id = R.drawable.grade),
            onClick = {
                navController.navigate(Screen.MarksPage.route)
            }
        ),
        MainMenuItem(
            title = "Resources",
            icon = painterResource(id = R.drawable.baseline_class_24),
            onClick = {
                navController.navigate(Screen.ResourcesPage.route)
            }
        ),
        MainMenuItem(
            title = "Settings",
            icon = painterResource(id = R.drawable.baseline_settings_24),
            onClick = {
                navController.navigate(Screen.SettingsPage.route)
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
    //Logic & UI,todo @LT @MAS #simple || explain the code  =
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
                        .height(860.dp)
                        .width(435.dp),
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
                                    contentDescription = null, modifier = Modifier.size(60.dp)
                                )
                                if (drawerState.isOpen) {
                                    Text(
                                        text = "Account",
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                } else {
                                    Text(text = "", style = MaterialTheme.typography.displayMedium)
                                }
                            }
                        }
                        LazyColumn {
                            items(menuItems.size) { item ->
                                Card(modifier = Modifier
                                    .padding(12.dp)
                                    .width(600.dp)
                                    .height(70.dp)
                                    .clickable {
                                        menuItems[item].onClick()
                                    }, colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )) {
                                    Column(modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp)) {
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
                    containerColor = MaterialTheme.colorScheme.primaryContainer, topBar = {
                        LargeTopAppBar(
                            title = {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Welcome,",
                                        style = MaterialTheme.typography.headlineMedium,
                                        modifier = Modifier.padding(start = 60.dp),
                                    )
                                    Text(
                                        text = "Laith",
                                        style = MaterialTheme.typography.headlineMedium,
                                        modifier = Modifier.padding(start = 70.dp)
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ), navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                }
                            }, modifier = Modifier
                        )
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
                                .padding(start = 70.dp, top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier)
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(600.dp)
                                    .height(200.dp)
                                    .clickable {
                                    }, colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
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
                                items(item.size) {
                                    Card(modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                topEnd = 25.dp,
                                                topStart = 25.dp,
                                                bottomEnd = 25.dp,
                                                bottomStart = 25.dp
                                            )
                                        )
                                        .padding(11.dp)
                                        .width(600.dp)
                                        .height(100.dp)
                                        .clickable { }, colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )) {
                                        Column(modifier = Modifier.fillMaxSize()) {
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