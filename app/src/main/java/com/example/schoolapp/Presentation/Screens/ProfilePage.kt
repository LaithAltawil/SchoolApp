package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.profilepagetable
import com.example.schoolapp.Presentation.Util.SimpleTable
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(MainViewModel: MainViewModel) {

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Scaffold(
                //Needs Completetion TODO
                topBar = {
                    LargeTopAppBar(title = { Text(text = "Profile") })

                }

            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                   Column(modifier = Modifier
                       .padding(5.dp)
                       .fillMaxSize(),
                       horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                       verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
                       Button(onClick = { /*TODO*/ },modifier = Modifier
                           .padding(16.dp)
                           .size(300.dp)) {
                           Image(
                               painter = painterResource(id = R.drawable.baseline_account_box_24),
                               contentDescription =null
                           )

                       }
                       //this must be changed because it doesnt look so good
                       SimpleTable(tableData = profilepagetable)

                   }


                }

            }


        }

    }


}

