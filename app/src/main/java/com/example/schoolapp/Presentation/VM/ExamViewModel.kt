package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExamViewModel : ViewModel() {
    private val _Examstate = MutableStateFlow(ExamPageState())
    val Examstate: StateFlow<ExamPageState> = _Examstate.asStateFlow()

    fun changeBottomSheetState(): Boolean {
        _Examstate.value = ExamPageState(showBottomSheet = !_Examstate.value.showBottomSheet)
        return _Examstate.value.showBottomSheet


    }

}