package com.example.schoolapp.Presentation.Screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CounselorTopAppBar
import com.example.schoolapp.Presentation.Util.DatePickerModal
import com.example.schoolapp.Presentation.VM.MainViewModel



@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CounselorPage(
    MainviewModel: MainViewModel=MainViewModel()
){
    LaunchedEffect(Unit) {
        MainviewModel.isTopappbarVisible3()
    }
    var selectedDate by remember { mutableStateOf("") }
    val state = MainviewModel.Counselorstate.collectAsStateWithLifecycle()

    AppTheme {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    CounselorTopAppBar(viewModel = MainviewModel,
                        modifier = Modifier,
                        "Counselor")
                },
                // Add content padding
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {

                    Button(onClick = {

                        MainviewModel.openDialog()
                        } ) {
                        Text("Select Date")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = "Selected Date:")
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = state.value.selectedDate)





                }
            }
            if(state.value.openDialog){
                DatePickerModal(onDateSelected = {
                    selectedDate=it.toString()

                }){
                    MainviewModel.closeDialog()
                }




            }



        }







    }


}