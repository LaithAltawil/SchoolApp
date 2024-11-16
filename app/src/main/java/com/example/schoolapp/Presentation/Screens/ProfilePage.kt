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
import com.example.schoolapp.Data.MockData.Mock.profilePageTable

import com.example.schoolapp.Presentation.Util.SimpleTable
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

//=======================================================
//solved @LT #simple || explain this fun logic here       =
//LT: this is the old profile page as it is getting replaced with the file
// Somethingiamtrying.kt

//=======================================================
//todo @LT #simple || @(33:17)=="MainViewModel" variable name must start with small litter
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
//IMPORTANT: OUTDATED DESIGN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(mainViewModel: MainViewModel) {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Scaffold(
                //Needs Completion TODO
                //@MAS I agree \(￣︶￣*\))
                //@LT no longer needed to complete this
                //check @(36:37)
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
                       SimpleTable(tableData = profilePageTable)

                   }


                }

            }


        }

    }


}

