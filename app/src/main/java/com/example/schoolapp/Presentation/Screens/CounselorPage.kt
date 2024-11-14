package com.example.schoolapp.Presentation.Screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Screens.ScreensPieces.CounselorTopAppBar
import com.example.schoolapp.Presentation.Util.DatePickerModal
import com.example.schoolapp.Presentation.VM.MainViewModel


//=======================================================
//Counselor page: UI & logic                            =
//=======================================================
//todo @LT #simple || @(37:69)=="mainviewModel" every world must be start with capital latter except the first
//todo @LT #medium~#hard || try adding the @preview notation to be able to use the design tab
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CounselorPage(
    mainviewModel: MainViewModel
) {

    //todo @MAS #simple || please add the usage after answering the referred todo task
    LaunchedEffect(Unit) {
        mainviewModel.isTopappbarVisible3()
    }

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = mainviewModel.Counselorstate.collectAsStateWithLifecycle()

    //=======================================================
    //UI & page logic                                       =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                //todo @MAS #simple || please add the usage after answering the referred todo task
                topBar = {
                    CounselorTopAppBar(
                        viewModel = mainviewModel,
                        modifier = Modifier,
                        "Counselor"
                    )
                },
                // Add content padding
            ) { innerPadding ->
                //todo @MAS #simple || please add the usage after answering the referred todo task
                Column(
                    modifier = Modifier.padding(innerPadding),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    //todo @MAS #simple || please add the usage after answering the referred todo task
                    Button(onClick = {
                        mainviewModel.openDialog()
                    }) {
                        Text("Select Date")
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = "Selected Date:")
                    Spacer(modifier = Modifier.size(16.dp))
                    //todo @MAS #simple || please add the usage after answering the referred todo task
                    Text(text = state.value.selectedDate)
                }
            }
            //todo @LT #simple || please add the usage for this part
            if (state.value.openDialog) {
                DatePickerModal(onDateSelected = {
                    mainviewModel.savedate(it.toString())
                }) {
                    mainviewModel.closeDialog()
                }
            }
        }
    }
}