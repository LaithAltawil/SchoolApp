package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        "Item 1",
        "Item 2",
        "Item 3"
    )
    AppTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top=90.dp)
                ) {
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)){
                        Row (modifier = Modifier.padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                            Text(text = "Account")

                        }
                    }
                    LazyColumn {
                        items(items.size) {
                            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)){
                                Text(text = items[it])

                            }
                        }

                    }

                }



            },
            content = {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                , topBar = {
                    LargeTopAppBar(title = { Text(text = "Menu",
                        style = MaterialTheme.typography.displayLarge)},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()

                            }

                        }){
                            Icon(imageVector = Icons.Default.List, contentDescription = null)
                        }
                    },modifier = Modifier.clip(
                            RoundedCornerShape(
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp
                            )
                        ))

                    }
                ) {
                    Column(modifier = Modifier.padding(it)) {

                        Text(text = "Content")
                    }


                }


            }


        )




    }


}