package com.example.schoolapp.Presentation.VM

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.schoolapp.Presentation.VM.States.CounselorsPageState
import com.example.schoolapp.Presentation.VM.States.ExamPageState
import com.example.schoolapp.Presentation.VM.States.HomeworkPageState
import com.example.schoolapp.Presentation.VM.States.MainDataClass
import com.example.schoolapp.Presentation.VM.States.MarkspageState
import com.example.schoolapp.Presentation.VM.States.SettingsPageState
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.type.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Locale

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class MainViewModel : ViewModel() {

    // Homework Page
    private val _state = MutableStateFlow(HomeworkPageState())
    val state: StateFlow<HomeworkPageState> = _state.asStateFlow()

    var State by mutableStateOf(MainDataClass.HomeworkPageState1())
            private set

    fun isLoading(){
        _state.value = HomeworkPageState(isLoading = !_state.value.isLoading)
    }



    private val _Examstate = MutableStateFlow(ExamPageState())
    val Examstate: StateFlow<ExamPageState> = _Examstate.asStateFlow()

    fun changeBottomSheetState(): Boolean {
        _Examstate.value = ExamPageState(showBottomSheet = !_Examstate.value.showBottomSheet)
        return _Examstate.value.showBottomSheet
    }

    //Marks Page
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



    //setting page
    private val _Settingstate = MutableStateFlow(SettingsPageState())
    val Settingstate: StateFlow<SettingsPageState> = _Settingstate.asStateFlow()
    fun isTopappbarVisible2() {
        _Settingstate.value = SettingsPageState(isTopBarVisible = !_Settingstate.value.isTopBarVisible)
    }
    //counselor Page
    private val _Counselorstate = MutableStateFlow(CounselorsPageState())
    val Counselorstate: StateFlow<CounselorsPageState> = _Counselorstate.asStateFlow()

    fun isTopappbarVisible3() {
        _Counselorstate.value = CounselorsPageState(topAppBarVisible = !_Counselorstate.value.topAppBarVisible)
    }
    fun openDialog() {
        _Counselorstate.value = CounselorsPageState(openDialog = true)
    }

    fun closeDialog(){
        _Counselorstate.value=CounselorsPageState(openDialog = false)

    }
    fun showDate():String{
        return _Counselorstate.value.selectedDate
    }


    fun updateSelectedDate(date: String) {
        Log.d("MainViewModel", "Updating selectedDate: $date")
        _Counselorstate.value.selectedDate = date
    }









}