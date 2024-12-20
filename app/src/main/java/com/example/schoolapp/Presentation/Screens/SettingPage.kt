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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Data.setting
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R


//solved @LT #please|| we are not doing this 😥
//@LT:we wont be doing theme and any hard thing
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    mainviewmodel: MainViewModel ,
    navController: NavController?
) {
    val state = mainviewmodel.Settingstate.collectAsStateWithLifecycle()
    //create A state for the settings page and add it to the main view model


    val settings = listOf(
        setting("Profile", painterResource(id = R.drawable.baseline_account_box_24),"Edit Profile") ,
        setting("Notifications", painterResource(id = R.drawable.baseline_notifications_24),"Notifications") ,
        setting("Help & FAQ", painterResource(id = R.drawable.help),"Help & FAQ") ,
        setting("Contact us", painterResource(id = R.drawable.contact_us),"Contact us")
    )


    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
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
                            modifier = Modifier.fillMaxSize(),

                        ) {
                            IconButton(
                                modifier = Modifier.padding(top = 50.dp, start = 5.dp),
                                onClick = {
                                    navController?.popBackStack()
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
                                text = "Settings", fontSize = 70.sp,
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                modifier = Modifier.padding(top=40.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                // Add content padding
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        itemsIndexed(settings) { index,item ->
                            Card(
                                colors = androidx.compose.material3.CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 6.dp, bottom = 6.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .height(50.dp)
                                    .width(350.dp)
                                    .clickable {
                                        mainviewmodel.showAlertDialog(index, true)

                                    }
                            ) {
                                if (state.value.showAlertDialog[index]) {
                                    AlertDialog(
                                        onDismissRequest = {
                                            mainviewmodel.showAlertDialog(index, false)

                                        },
                                        title = { Text("Pop-up Title") },
                                        text = { Text("This is the pop-up content.") },
                                        confirmButton = {
                                            Button(onClick = {
                                                mainviewmodel.showAlertDialog(index, false)

                                            }) {
                                                Text("OK")
                                            }
                                        }
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp)
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                                ) {
                                    Row {
                                        item.imagePath?.let { it1 ->
                                            Icon(
                                                painter = it1,
                                                contentDescription = null,
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = item.name)
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                                                contentDescription = null
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




