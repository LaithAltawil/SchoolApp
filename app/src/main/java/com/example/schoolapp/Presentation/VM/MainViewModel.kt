package com.example.schoolapp.Presentation.VM

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.schoolapp.Presentation.VM.States.MainDataClass
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//Main viewModel
class MainViewModel(private val context: Context) : ViewModel() {

    //=======================================================
    //Repository: Student                                   =
    //=======================================================
    private val studentRepository = StudentRepository(
        StudentDatabase.getInstance(context))

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    // Homework Page
    private val _state = MutableStateFlow(MainDataClass.HomeworkPageState1())
    val state: StateFlow<MainDataClass.HomeworkPageState1> = _state.asStateFlow()

    //Exam Page
    private val _Examstate = MutableStateFlow(MainDataClass.ExamPageState1())
    val Examstate: StateFlow<MainDataClass.ExamPageState1> = _Examstate.asStateFlow()

    //Marks Page
    private val _Marksstate = MutableStateFlow(MainDataClass.MarkspageState1())
    val Marksstate: StateFlow<MainDataClass.MarkspageState1> = _Marksstate.asStateFlow()

    //setting page
    private val _Settingstate = MutableStateFlow(MainDataClass.SettingsPageState1())
    val Settingstate: StateFlow<MainDataClass.SettingsPageState1> = _Settingstate.asStateFlow()


    //ClassesPage
    private val _Classesstate = MutableStateFlow(MainDataClass.ClassesPageState())
    val Classesstate: StateFlow<MainDataClass.ClassesPageState> = _Classesstate.asStateFlow()

    //CalenderPage
    private val _Calenderstate = MutableStateFlow(MainDataClass.CalenderPage())
    val Calenderstate: StateFlow<MainDataClass.CalenderPage> = _Calenderstate.asStateFlow()
    //=======================================================
    //functions: public & private                           =
    //=======================================================
    //todo @LT #simple|| please format the file with the provided pattern

    fun updateBottomSheetState(index: Int, newState: Boolean) {
        // Ensure index is within bounds
        if (index in _Examstate.value.BottomSheet.indices) {
            _Examstate.value.BottomSheet[index] = newState
        }
    }

    fun updateBottomSheetState1(index: Int, newState: Boolean) {
        // Ensure index is within bounds
        if (index in _Marksstate.value.BottomSheet.indices) {
            _Marksstate.value.BottomSheet[index] = newState
        }
    }

    fun showAlertDialog(index: Int, newState: Boolean){
        if (index in _Settingstate.value.showAlertDialog.indices) {
            _Settingstate.value.showAlertDialog[index] = newState
        }
    }

    //counselor Page
//    private val _Counselorstate = MutableStateFlow(MainDataClass.CounselorsPageState1())
//    val Counselorstate: StateFlow<MainDataClass.CounselorsPageState1> =
//        _Counselorstate.asStateFlow()
//
//    fun isTopappbarVisible3() {
//        _Counselorstate.value =
//            MainDataClass.CounselorsPageState1(isTopAppBarVisible = !_Counselorstate.value.isTopAppBarVisible)
//    }
//
//    fun openDialog() {
//        _Counselorstate.value = MainDataClass.CounselorsPageState1(openDialog = true)
//    }
//
//    fun closeDialog() {
//        _Counselorstate.value = MainDataClass.CounselorsPageState1(openDialog = false)
//    }
//
//    fun savedate(s: String) {
//        _Counselorstate.value = MainDataClass.CounselorsPageState1(selectedDate = s)
//    }

    //ResourcesPage
    private val _Resourcesstate = MutableStateFlow(MainDataClass.ResourcesPageState())
    val Resourcesstate: StateFlow<MainDataClass.ResourcesPageState> = _Resourcesstate.asStateFlow()
    fun updateBottomSheetState2(index: Int, newState: Boolean) {
        // Ensure index is within bounds
        if (index in _Resourcesstate.value.BottomSheet1.indices) {
            _Resourcesstate.value.BottomSheet1[index] = newState
        }
    }

    fun isTopappbarVisible5() {
        _Classesstate.value =
            MainDataClass.ClassesPageState(isTopAppBarVisible = !_Classesstate.value.isTopAppBarVisible)
    }

}