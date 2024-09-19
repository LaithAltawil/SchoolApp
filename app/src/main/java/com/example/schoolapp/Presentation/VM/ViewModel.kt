package com.example.schoolapp.Presentation.VM

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _signInState = MutableStateFlow(SignInPageState())
    val signInState: StateFlow<SignInPageState> = _signInState.asStateFlow()

    fun ChangeUserName(User:String){
        _signInState.value = _signInState.value.copy(UserName = User)
    }

    fun ChangePassword(Pass:String){
        _signInState.value = _signInState.value.copy(password = Pass)
    }










}