package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import com.example.schoolapp.Presentation.VM.States.SignInPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel() : ViewModel() {
    private val _signInState = MutableStateFlow(SignInPageState())
    val signInState: StateFlow<SignInPageState> = _signInState.asStateFlow()
    fun onUserNameChange(userName: String) {
        _signInState.update { it.copy(UserName = userName) }
    }

    fun onPasswordChange(password: String) {
        _signInState.update { it.copy(password = password) }
    }

    fun onPasswordVisibilityChange() {
        _signInState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

}
