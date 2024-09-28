package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Screens.ScreensPieces.MyTopAppBar
import com.example.schoolapp.Presentation.Util.DatePicker
import com.example.schoolapp.Presentation.VM.MainViewModel

@Composable
fun CounselorPage(
    viewModel: MainViewModel=MainViewModel()
){
    LaunchedEffect(Unit) {
        viewModel.isTopappbarVisible3()
    }

    val state by viewModel.Counselorstate.collectAsStateWithLifecycle()
    val context = LocalContext.current
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer){
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    MyTopAppBar(viewModel = viewModel,
                        modifier = Modifier,
                        "Counselor Page")
                },
                // Add content padding
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ){
                    Column(modifier = Modifier.fillMaxSize()
                        ,horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally)
                    {
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){

                            Button(onClick = {

                                state.Date= android.widget.DatePicker(context).toString()

                            }, colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                                ) {
                                Text(text = "Pick a date")
                            }
                            TextField(value = state.Date,
                                onValueChange = {state.Date=it}
                            )
                        }
                        Spacer(modifier = Modifier.padding(15.dp))
                        TextField(value = state.Id,onValueChange = {state.Id=it})
                        Spacer(modifier = Modifier.padding(15.dp))
                        Button(onClick = { /*TODO*/ }, enabled =viewModel.IsIDandDateEntered() ) {
                            Text(text = "Submit")
                        }









                    }

                }

            }


        }




    }


}