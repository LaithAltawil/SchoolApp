package com.example.schoolapp.Navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.schoolapp.Presentation.Screens.CalenderPage
import com.example.schoolapp.Presentation.Screens.CounselorPage

import com.example.schoolapp.Presentation.Screens.ExamsPage
import com.example.schoolapp.Presentation.Screens.HomeworkPage
import com.example.schoolapp.Presentation.Screens.MainMenu
import com.example.schoolapp.Presentation.Screens.MarksPage
import com.example.schoolapp.Presentation.Screens.Profile_page
import com.example.schoolapp.Presentation.Screens.ResourcesPage
import com.example.schoolapp.Presentation.Screens.SettingPage
import com.example.schoolapp.Presentation.Screens.SignIn
import com.example.schoolapp.Presentation.Screens.StartPage
import com.example.schoolapp.Presentation.Screens.StudentClass
import com.example.schoolapp.Presentation.VM.AppViewModel
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.Presentation.VM.VMF.AppViewModelFactory
import com.example.schoolapp.Presentation.VM.VMF.MainViewModelFactory

//=======================================================
//navigation logic                                      =
//=======================================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    //=======================================================
    //navigation                                            =
    //=======================================================
    //function created from navController library*/
    val navController = rememberNavController()
    //navigation logic
    NavHost(navController = navController, startDestination = "Start") {
        //starter navigation map
        navigation(
            startDestination = Screen.StartPage.route,
            route = "Start"
        ) {
            //start page navigation
            composable(Screen.StartPage.route) {
                StartPage() {
                    navController.navigate(Screen.SignInPage.route)
                }
            }
            //sign in page navigation & Home map navigation
            composable(Screen.SignInPage.route) { entry ->
                val context = LocalContext.current
                val viewModel = entry.AppViewModel<AppViewModel>(navController, context)
                SignIn(viewModel) {
                    navController.navigate("Home")
                }
            }
        }
        //home map navigation
        navigation(
            startDestination = Screen.MainMenu.route,
            route = "Home"
        ) {
            //main menu navigation
            composable(Screen.MainMenu.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                MainMenu(viewModel, navController)
            }
            //profile page navigation
            composable(Screen.ProfilePage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                Profile_page(
                    viewModel,
                    navController = navController
                )
            }
            composable(Screen.CalenderPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                CalenderPage(
                    mainViewModel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.CounselorPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                CounselorPage(
                    navController = navController,
                    mainViewModel = viewModel
                )
            }
            composable(Screen.HomeworkPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                HomeworkPage(
                    viewModel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.SettingsPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                SettingPage(
                    mainviewmodel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.MarksPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                MarksPage(
                    mainviewmodel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.ResourcesPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                ResourcesPage(
                    mainviewmodel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.ExamsPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                ExamsPage(
                    mainViewModel = viewModel,
                    navController = navController
                )
            }
            composable(Screen.ClassesPage.route) {
                val context = LocalContext.current
                val viewModel = it.MainViewModel<MainViewModel>(navController, context)
                StudentClass(
                    mainViewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

//=======================================================
//obtain a ViewModel scoped to a navigation graph       =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.AppViewModel(
    navController: NavHostController,
    context: Context
): T {
    // Get the route of the parent navigation graph
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    // Get the NavBackStackEntry for the parent navigation graph
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    //creating factory to bind it with the viewModel
    val factory = AppViewModelFactory(context) // Use your custom factory
    //return the viewModel scoped to the parent entry
    return viewModel(parentEntry, factory = factory)
}

//=======================================================
//obtain a ViewModel scoped to a navigation graph       =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.MainViewModel(
    navController: NavHostController,
    context: Context
): T {
    // Get the route of the parent navigation graph
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    // Get the NavBackStackEntry for the parent navigation graph
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    //creating factory to bind it with the viewModel
    val factory = MainViewModelFactory(context) // Use your custom factory
    // Return the ViewModel scoped to the parent entry
    return viewModel(parentEntry, factory = factory)
}