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
import com.example.schoolapp.Presentation.Screens.ProfilePage
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
    /*solved @LT #qustion[answered] || did you create this rememberNavController
     or its imported from other library?
    function created from navController library*/
    val navController = rememberNavController()

    /*solved @LT [#IMPOSSIBLE] || explain this to me @MAS 😃
    LT: when you see it call me or send me a message to explain it*/
    NavHost(navController = navController, startDestination = "Start") {

        navigation(
            startDestination = Screen.StartPage.route,
            route = "Start"
        ) {
            composable(Screen.StartPage.route) {
                StartPage() {
                    navController.navigate(Screen.SignInPage.route)
                }
            }

            //todo @LT #simple || variable name must start with small capital latter
            composable(Screen.SignInPage.route) { entry ->
                val viewModel = entry.AppViewModel<AppViewModel>(navController)
                SignIn(viewModel) {
                    navController.navigate("Home")
                }
            }
        }

        navigation(
            startDestination = Screen.MainMenu.route,
            route = "Home"
        ) {
            composable(Screen.MainMenu.route) {
                MainMenu(navController)
            }
            composable(Screen.ProfilePage.route) {
                ProfilePage(
                    mainViewModel = viewModel
                )
            }
            composable(Screen.CalenderPage.route) {
                CalenderPage(
                    mainViewModel = viewModel
                )
            }
            composable(Screen.CounselorPage.route) {
                CounselorPage(
                    mainviewModel = viewModel
                )
            }
            composable(Screen.HomeworkPage.route) {
                HomeworkPage(
                    homeworkpagestate = viewModel
                )
            }
            composable(Screen.SettingsPage.route) {
                SettingPage(
                    MainViewModel = viewModel
                )

            }
            composable(Screen.MarksPage.route) {
                MarksPage(
                    MainViewModel = viewModel
                )

            }
            composable(Screen.ResourcesPage.route) {
                ResourcesPage(
                    MainViewModel = viewModel
                )

            }
            composable(Screen.ExamsPage.route) {
                ExamsPage(
                    mainviewmodel = viewModel
                )

            }
            composable(Screen.ClassesPage.route) {
                StudentClass(
                    mainviewmodel = viewModel
                )
            }
        }
    }
}

//=======================================================
//not solved @LT #simple || explain this fun logic here =
//LT: not sure :) but saw it from someone on youtube:)) =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.AppViewModel(
    navController: NavHostController,
): T {
    //not solved @LT [#IMPOSSIBLE] || explain this to me @MAS 😃

    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

//=======================================================
//not solved @LT #simple || explain this fun logic here =
//LT: saw on youtube not sure 100%                      =
//=======================================================
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.MainViewModel(
    navController: NavHostController,
): T {
    //todo @LT [#IMPOSSIBLE] || explain this to me @MAS 😃
    //inform me to explain to you
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}