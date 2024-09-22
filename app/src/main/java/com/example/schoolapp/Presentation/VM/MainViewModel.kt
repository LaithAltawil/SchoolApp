package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel(){
    private val _state = MutableStateFlow(HomeworkPageState())
    val state: StateFlow<HomeworkPageState> = _state.asStateFlow()










}