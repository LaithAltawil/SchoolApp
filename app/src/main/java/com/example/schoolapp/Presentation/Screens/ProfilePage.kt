package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//todo @LT #simple || plz fix this
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.profilepagetable
import com.example.schoolapp.Presentation.Util.SimpleTable
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

//=======================================================
//todo @LT #simple || explain this fun logic here       =
//=======================================================
//todo @LT #simple || @(33:17)=="MainViewModel" variable name must start with small litter
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(MainViewModel: MainViewModel) {

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
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize(),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Button(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .padding(16.dp)
                                .size(300.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_account_box_24),
                                contentDescription = null
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