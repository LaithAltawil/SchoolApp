package com.example.schoolapp.Navigation

sealed class Screen(val route: String){
    object StartPage: Screen("start_page")
    object SignInPage: Screen("sign_in_page")
    object MainMenu: Screen("main_menu")
    object CalenderPage: Screen("calender_page")
    object CounselorPage: Screen("counselor_page")

}