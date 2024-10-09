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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        "Item 3",
        "Item 4",
        "Item 5",
        "Item 6",
        "Item 7",
        "Item 8",
    )
    AppTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Surface(modifier =Modifier.clip(RoundedCornerShape(
                    topEnd = 25.dp,
                    topStart = 25.dp,
                    bottomEnd = 25.dp,
                    bottomStart = 25.dp
                )),color = MaterialTheme.colorScheme.primary
                    ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 90.dp)
                    ) {
                        Button(onClick = { /*TODO*/ }, modifier = Modifier
                            .padding(5.dp)
                            .width(800.dp)){
                            Row (modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween){
                                Icon(imageVector = Icons.Default.AccountBox,
                                    contentDescription = null,modifier = Modifier.size(60.dp))
                                if(drawerState.isOpen){
                                    Text(text = "Account",style = MaterialTheme.typography.displayMedium)
                                }else{
                                    Text(text = "",style = MaterialTheme.typography.displayMedium)
                                }

                            }
                        }
                        LazyColumn {
                            items(items.size) {
                                Card(modifier = Modifier
                                    .padding(10.dp)
                                    .width(600.dp)
                                    .height(60.dp)
                                    .clickable { }
                                    ,colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    )) {
                                    Column(modifier = Modifier.fillMaxSize()) {

                                    }

                                }
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
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(start = 100.dp))},
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
                    Column(modifier = Modifier
                        .padding(it)
                        .fillMaxSize()) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier)
                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(600.dp)
                                    .height(200.dp)
                                    .clickable { },colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )
                            ) {

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Un finished Homeworks",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.secondary

                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyColumn {
                                items(items.size) {
                                    Card(modifier = Modifier.clip(
                                        RoundedCornerShape(
                                            topEnd = 25.dp,
                                            topStart = 25.dp,
                                            bottomEnd = 25.dp,
                                            bottomStart = 25.dp
                                        )).padding(15.dp)
                                        .width(600.dp)
                                        .height(100.dp)
                                        .clickable { }
                                        ,colors = CardDefaults.cardColors(
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