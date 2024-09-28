package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import com.example.schoolapp.Presentation.VM.States.ExamPageState
import com.example.schoolapp.Presentation.VM.States.HomeworkPageState
import com.example.schoolapp.Presentation.VM.States.MarkspageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeworkPageState())
    val state: StateFlow<HomeworkPageState> = _state.asStateFlow()

    private val _Examstate = MutableStateFlow(ExamPageState())
    val Examstate: StateFlow<ExamPageState> = _Examstate.asStateFlow()

    fun changeBottomSheetState(): Boolean {
        _Examstate.value = ExamPageState(showBottomSheet = !_Examstate.value.showBottomSheet)
        return _Examstate.value.showBottomSheet
    }

    private val _Marksstate = MutableStateFlow(MarkspageState())
    val Marksstate: StateFlow<MarkspageState> = _Marksstate.asStateFlow()

    fun changeBottomSheetState2(item: Int) {
        // Assuming showBottomSheet is a MutableList<Boolean> in your MarkspageState

        val updatedShowBottomSheet = _Marksstate.value.showBottomSheet.toMutableList() // Create a mutable copy
        updatedShowBottomSheet[item] = !updatedShowBottomSheet[item] // Toggle the value at the specified index
        _Marksstate.value = _Marksstate.value.copy(showBottomSheet = updatedShowBottomSheet) // Update the state
    }
    fun isTopappbarVisible() {
        _Marksstate.value = MarkspageState(isTopBarVisible = !_Marksstate.value.isTopBarVisible)
    }

}