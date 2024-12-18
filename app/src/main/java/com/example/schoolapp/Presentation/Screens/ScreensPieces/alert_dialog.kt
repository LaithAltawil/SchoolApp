package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//This will be the alert dialog appearing when there is something wrong at sign in
@Composable
fun Alert_Dialog(text:String){
    AlertDialog(
        onDismissRequest = {


        },
        title = { Text("Cant sign in") },
        text = { Text(text) },
        confirmButton = {
            Button(onClick = {


            }) {
                Text("OK")
            }
        }
    )


}