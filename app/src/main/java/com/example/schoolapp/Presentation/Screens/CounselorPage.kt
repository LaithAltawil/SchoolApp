package com.example.schoolapp.Presentation.Screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme

import com.example.schoolapp.MainActivity
import com.example.schoolapp.Presentation.Screens.ScreensPieces.MyTopAppBar

import com.example.schoolapp.Presentation.VM.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale.getDefault

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounselorPage(
    MainviewModel: MainViewModel=MainViewModel()
){
    LaunchedEffect(Unit) {

        MainviewModel.isTopappbarVisible()
    }
    Log.d("CounselorPage", "Recomposing")
    val state by MainviewModel.Counselorstate.collectAsStateWithLifecycle()
    var date by remember { mutableStateOf("") }




    AppTheme {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    MyTopAppBar(viewModel = MainviewModel,
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
                    Text(text = date)





                }
            }
            if(state.openDialog){
                val datePickerDialog = rememberDatePickerState()
                val confirmEnabled = derivedStateOf {
                    datePickerDialog.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = {
                        MainviewModel.closeDialog()
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            MainviewModel.closeDialog()
                            if(datePickerDialog.selectedDateMillis!=null){
                                state.selectedDate=
                                    datePickerDialog.selectedDateMillis.toString()
                            }
                            date=MainviewModel.showDate()





                        }, enabled = confirmEnabled.value
                        ) {
                            Text("OK")

                        }

                    },

                ) {
                    DatePicker(state = datePickerDialog)


                }




            }



        }







    }


}