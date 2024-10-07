package com.example.schoolapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.compose.AppTheme
import com.example.schoolapp.Navigation.Navigation

import com.example.schoolapp.Presentation.Screens.CalenderPage
import com.example.schoolapp.Presentation.Screens.CounselorPage
import com.example.schoolapp.Presentation.Screens.MainMenu
import com.example.schoolapp.Presentation.Screens.SignIn
import com.example.schoolapp.Presentation.Screens.StartPage

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {

                MainMenu()



            }
        }
    }
}
