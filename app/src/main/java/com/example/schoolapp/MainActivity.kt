package com.example.schoolapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.example.compose.AppTheme
import com.example.schoolapp.Navigation.Navigation
import com.example.schoolapp.Presentation.Screens.CounselorPage
import com.example.schoolapp.Presentation.Screens.HomeworkPage
import com.example.schoolapp.Presentation.Screens.MarksPage
import com.example.schoolapp.Presentation.Screens.ProfilePage
import com.example.schoolapp.Presentation.Screens.SettingPage
import com.example.schoolapp.Presentation.Screens.StartPage

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {

                CounselorPage()



            }
        }
    }
}
