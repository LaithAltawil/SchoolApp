package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow( )
    val uiState: StateFlow< > = _uiState.asStateFlow()

}