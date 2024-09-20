package com.example.schoolapp.Presentation.VM

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
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

}
