package com.example.schoolapp.Presentation.VM.States

data class SignInPageState(
    val UserName: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)