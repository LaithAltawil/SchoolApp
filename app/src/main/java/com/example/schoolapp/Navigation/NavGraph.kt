package com.example.schoolapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.schoolapp.Presentation.Screens.SignIn
import com.example.schoolapp.Presentation.Screens.StartPage
import com.example.schoolapp.Presentation.VM.AppViewModel

@Composable
fun Navigation() {
    val ViewModel= AppViewModel()

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Start") {
        navigation(
            startDestination = Screen.StartPage.route,
            route = "Start"
        ) {
            composable(Screen.StartPage.route) {
                StartPage()
            }
            composable(Screen.SignInPage.route) {
                SignIn(ViewModel)
            }
        }


    }
}