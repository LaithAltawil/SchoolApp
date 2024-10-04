package com.example.schoolapp.Presentation.Screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Data.Subjects
import com.example.schoolapp.Presentation.Screens.ScreensPieces.SettingsTopAppBar
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    MainViewModel: MainViewModel = MainViewModel()
) {
    val state = MainViewModel.Settingstate.collectAsStateWithLifecycle()
    //create A state for the settings page and add it to the main view model
    LaunchedEffect(Unit) {
        MainViewModel.isTopappbarVisible2()
    }


    val settings = listOf(
        Subjects("Profile", painterResource(id = R.drawable.baseline_account_box_24)) {},
        Subjects("Notifications", painterResource(id = R.drawable.baseline_notifications_24)) {},
        Subjects("Theme", painterResource(id = R.drawable.help)) {},
        Subjects("Accessibility", painterResource(id = R.drawable.contact_us)) {},
        Subjects("Help & FAQ", painterResource(id = R.drawable.help)) {},
        Subjects("Contact us", painterResource(id = R.drawable.contact_us)) {}

    )

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
                    SettingsTopAppBar(
                        viewModel =
                        MainViewModel,
                        modifier = Modifier,
                        Title = "Settings"
                    )
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
                        items(settings) { item ->
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


                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp)
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                                ) {
                                    Row {
                                        item.ImagePath?.let { it1 ->
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

