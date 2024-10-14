package com.example.schoolapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.schoolapp.Presentation.Screens.MainMenu
import com.example.schoolapp.Presentation.Screens.ProfilePage
import com.example.schoolapp.Presentation.Screens.SignIn
import com.example.schoolapp.Presentation.Screens.StartPage
import com.example.schoolapp.Presentation.VM.AppViewModel
import com.example.schoolapp.Presentation.VM.MainViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Start") {

        navigation(
            startDestination = Screen.StartPage.route,
            route = "Start"
        ) {
            composable(Screen.StartPage.route) {
                StartPage(){
                    navController.navigate(Screen.SignInPage.route)
                }
            }

            composable(Screen.SignInPage.route) {
                    entry ->
                val ViewModel = entry.AppViewModel<AppViewModel>(navController,)
                SignIn(ViewModel){
                    navController.navigate("Home")
                }
            }
        }
        navigation(
            startDestination = Screen.MainMenu.route,
            route = "Home"
        ){
            composable(Screen.MainMenu.route){
                MainMenu(navController)
            }
            composable(Screen.ProfilePage.route){
                    entry ->
                val ViewModel = entry.MainViewModel<MainViewModel>(navController)

               ProfilePage(
                   MainViewModel = ViewModel
               )
            }


        }


    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.AppViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.MainViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}