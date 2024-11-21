package com.example.schoolapp.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

//=======================================================
//navigation logic                                      =
//=======================================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {

    //=======================================================
    //viewModel                                             =
    //=======================================================
    val viewModel = viewModel<MainViewModel>()

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
                    //todo @LT #qustion|| here are you parsing the navigation as function?
                    navController.navigate(Screen.SignInPage.route)
                }
            }

            //sign in page navigation & Home map navigation
            composable(Screen.SignInPage.route) { entry ->
                val viewModel = entry.AppViewModel<AppViewModel>(navController)
                SignIn(viewModel) {
                    //todo @LT #qustion|| here are you parsing the navigation map as function?
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
                //todo @LT #qustion|| here are you parsing the navigation as function?
                MainMenu(navController)
            }

            //profile page navigation
            composable(Screen.ProfilePage.route) {
                //todo @LT #qustion|| what is this?
                Profile_page()
            }


            composable(Screen.CalenderPage.route) {
                CalenderPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    mainViewModel = viewModel
                )
            }
            composable(Screen.CounselorPage.route) {
                CounselorPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    mainviewModel = viewModel
                )
            }
            composable(Screen.HomeworkPage.route) {
                HomeworkPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    homeworkpagestate = viewModel
                )
            }
            composable(Screen.SettingsPage.route) {
                SettingPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    MainViewModel = viewModel
                )

            }
            composable(Screen.MarksPage.route) {
                MarksPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    //MAS: note it was MainViewModel then I fixed it to mainviewmodel
                    mainviewmodel = viewModel
                )

            }
            composable(Screen.ResourcesPage.route) {
                ResourcesPage(
                    mainviewmodel = viewModel
                )

            }
            composable(Screen.ExamsPage.route) {
                ExamsPage(
                    //todo @LT #qustion|| why didn't you parse the navigation instead of the viewModel?
                    mainviewmodel = viewModel
                )

            }
            composable(Screen.ClassesPage.route) {
                StudentClass(
                    mainViewModel = viewModel
                )
            }
        }
    }
}

//todo @LT #meduim || explain this comments :)
//=======================================================
//obtain a ViewModel scoped to a navigation graph       =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.AppViewModel(
    navController: NavHostController,
): T {
    // Get the route of the parent navigation graph
    val navGraphRoute = destination.parent?.route ?: return viewModel()

    // Get the NavBackStackEntry for the parent navigation graph
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    // Return the ViewModel scoped to the parent entry
    return viewModel(parentEntry)
}

//=======================================================
//obtain a ViewModel scoped to a navigation graph       =                                                     =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.MainViewModel(
    navController: NavHostController,
): T {
    // Get the route of the parent navigation graph
    val navGraphRoute = destination.parent?.route ?: return viewModel()

    // Get the NavBackStackEntry for the parent navigation graph
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    // Return the ViewModel scoped to the parent entry
    return viewModel(parentEntry)
}