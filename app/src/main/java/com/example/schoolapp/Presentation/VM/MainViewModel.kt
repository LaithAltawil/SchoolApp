package com.example.schoolapp.Presentation.VM

import androidx.lifecycle.ViewModel
import com.example.schoolapp.Presentation.VM.States.MainDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//Main viewModel
class MainViewModel : ViewModel() {

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    //todo @LT #simple|| please format the file with the provided pattern
    //=======================================================
    //functions: public & private                           =
    //=======================================================
    //todo @LT #simple|| please format the file with the provided pattern

    // Homework Page
    private val _state = MutableStateFlow(MainDataClass.HomeworkPageState1())
    val state: StateFlow<MainDataClass.HomeworkPageState1> = _state.asStateFlow()


    //Exam Page
    private val _Examstate = MutableStateFlow(MainDataClass.ExamPageState1())
    val Examstate: StateFlow<MainDataClass.ExamPageState1> = _Examstate.asStateFlow()

    fun changeBottomSheetState(): Boolean {
        _Examstate.value =
            MainDataClass.ExamPageState1(showBottomSheet = !_Examstate.value.showBottomSheet)
        return _Examstate.value.showBottomSheet
    }

    //Marks Page
    private val _Marksstate = MutableStateFlow(MainDataClass.MarkspageState1())
    val Marksstate: StateFlow<MainDataClass.MarkspageState1> = _Marksstate.asStateFlow()

    fun changeBottomSheetState2(item: Int) {
        // Assuming showBottomSheet is a MutableList<Boolean> in your MarkspageState

        val updatedShowBottomSheet =
            _Marksstate.value.showBottomSheet.toMutableList() // Create a mutable copy
        updatedShowBottomSheet[item] =
            !updatedShowBottomSheet[item] // Toggle the value at the specified index
        _Marksstate.value =
            _Marksstate.value.copy(showBottomSheet = updatedShowBottomSheet) // Update the state
    }

    fun isTopappbarVisible() {
        _Marksstate.value =
            MainDataClass.MarkspageState1(isTopBarVisible = !_Marksstate.value.isTopBarVisible)
    }

    //setting page
    private val _Settingstate = MutableStateFlow(MainDataClass.SettingsPageState1())
    val Settingstate: StateFlow<MainDataClass.SettingsPageState1> = _Settingstate.asStateFlow()


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



    fun changeBottomSheetState(item: Int) {
        val updatedShowBottomSheet = _Resourcesstate.value.showBottomSheet.toMutableList()
        updatedShowBottomSheet[item] = !updatedShowBottomSheet[item]
        _Resourcesstate.value = _Resourcesstate.value.copy(showBottomSheet = updatedShowBottomSheet)
    }

    //ClassesPage
    private val _Classesstate = MutableStateFlow(MainDataClass.ClassesPageState())
    val Classesstate: StateFlow<MainDataClass.ClassesPageState> = _Classesstate.asStateFlow()

    fun isTopappbarVisible5() {
        _Classesstate.value =
            MainDataClass.ClassesPageState(isTopAppBarVisible = !_Classesstate.value.isTopAppBarVisible)
    }

    //CalenderPage
    private val _Calenderstate = MutableStateFlow(MainDataClass.CalenderPage())
    val Calenderstate: StateFlow<MainDataClass.CalenderPage> = _Calenderstate.asStateFlow()
    fun isTopappbarVisible6() {
        _Calenderstate.value =
            MainDataClass.CalenderPage(isTopAppBarVisible = !_Calenderstate.value.isTopAppBarVisible)
    }
}