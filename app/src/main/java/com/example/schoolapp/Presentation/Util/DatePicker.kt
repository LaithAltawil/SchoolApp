package com.example.schoolapp.Presentation.Util

import androidx.compose.material3.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.util.Calendar


@Composable
fun DatePicker(context: Context,
               MainViewModel: MainViewModel){
    val state by MainViewModel.Counselorstate.collectAsState()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                state.Date = "$dayOfMonth/${month + 1}/$year" // Format the date as needed
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )


}