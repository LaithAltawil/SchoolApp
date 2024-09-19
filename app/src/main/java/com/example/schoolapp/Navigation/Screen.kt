package com.example.schoolapp.Navigation

sealed class Screen(val route: String){
    object StartPage: Screen("start_page")
    object SignInPage: Screen("sign_in_page")

}