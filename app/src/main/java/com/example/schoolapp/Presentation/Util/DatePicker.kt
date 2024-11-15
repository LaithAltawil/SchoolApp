package com.example.schoolapp.Presentation.Util

import androidx.compose.material3.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.material3.DatePicker
import androidx.compose.runtime.remember
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.example.schoolapp.Presentation.VM.MainViewModel
import java.util.Calendar

//=======================================================
//used to pick dates                                    =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val datePickerState = rememberDatePickerState()

    //todo @LT #simple || please add usage for this, I need full details plz
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}