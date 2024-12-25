package com.example.schoolapp.Presentation.VM

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.Presentation.VM.States.HomeworkLoadingState
import com.example.schoolapp.Presentation.VM.States.MainDataClass
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.repository.StudentRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//Main viewModel
class MainViewModel(private val context: Context) : ViewModel() {

    //===========================================================================================
    //Repository: Student                                                                       =
    //===========================================================================================
    private val studentRepository = StudentRepository(
        StudentDatabase.getInstance(context)
    )

    //============================================================================================
    //variables: states                                                                          =
    //============================================================================================
    // student                                              =
    //=======================================================
    //handles ROOM operations for student
    private val _student = MutableStateFlow<Student?>(null) // Initialize with null
    val student: StateFlow<Student?> = _student.asStateFlow()

    //=======================================================
    // main menu page                                       =
    //=======================================================
    //homework object handles ROOM operations for main menu
    private val _localHomeworkList = MutableStateFlow<List<Homework>?>(null) // Initialize with null
    val localHomeworkList: StateFlow<List<Homework>?> = _localHomeworkList.asStateFlow()

    //homework object handles UI operations for main menu
    private val _homeworkList =
        MutableStateFlow<List<Homework>?>(emptyList()) // Initialize with empty list
    val homeworkList: StateFlow<List<Homework>?> = _homeworkList.asStateFlow()

    //homework int represent the last id number
    private val _lastHomeworkId = MutableStateFlow<Int?>(null) // Initialize with null
    val lastHomeworkId: StateFlow<Int?> = _lastHomeworkId.asStateFlow()

    //homework string represent the last homework class
    private val _lastHomeworkClass = MutableStateFlow<String?>(null) // Initialize with null
    val lastHomeworkClass: StateFlow<String?> = _lastHomeworkClass.asStateFlow()

    //handle the homework loading state operations
    private val _loadingState = MutableStateFlow<HomeworkLoadingState>(HomeworkLoadingState.Initial)
    val loadingState: StateFlow<HomeworkLoadingState> = _loadingState.asStateFlow()

    //=======================================================
    //profile                                               =
    //=======================================================
    //handle parent operations
    private val _parent = MutableStateFlow<Parent?>(null) // Initialize with null
    val parent: StateFlow<Parent?> = _parent.asStateFlow()

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

    // Homework Page
    private val _state = MutableStateFlow(MainDataClass.HomeworkPageState1())
    val state: StateFlow<MainDataClass.HomeworkPageState1> = _state.asStateFlow()

    //=======================================================
    // Local variables                                      =
    //=======================================================

    //===========================================================================================
    // ROOM side                                                                                =
    //===========================================================================================
    // student                                              =
    //=======================================================
    private suspend fun setStudent() {
            _student.value = studentRepository.setStudent()
    }

    //=======================================================
    // main menu                                            =
    //=======================================================
    //insert homework from the API list
    private suspend fun insertHomework(homework: Homework) {
        studentRepository.insertHomework(homework)
    }

    //delete all homework
    private suspend fun deleteAllHomework() {
        studentRepository.deleteAllHomework()
    }

    //get all the homework list from the local ROOM
    private suspend fun getAllHomeworkList() {
        _localHomeworkList.value = studentRepository.getAllHomework()
    }

    //get last homework id for DB query
    private suspend fun getLastHomeworkId() {
        _lastHomeworkId.value = studentRepository.getLastHomeworkId()
    }

    //get last homework class for DB query
    private suspend fun getLastHomeworkClass() {
        _lastHomeworkClass.value = studentRepository.getLastHomeworkClass()
    }

    //getting the homeworks for the todo_list, condition > current date
    private suspend fun getHomeworkList() {
        //get all the homework in the local database.
        //where homework date is starting from the current date.
        _homeworkList.value = studentRepository.getHomeworkForTodo()
    }

    //=======================================================
    //profile                                               =
    //=======================================================
    private suspend fun insertParent(parent: Parent) {
            studentRepository.insertParent(parent)
    }

    private suspend fun setParent() {
            _parent.value = studentRepository.setParent()
    }

    private fun deleteParent() {
        viewModelScope.launch {
            studentRepository.deleteParent()
        }
    }

    //===========================================================================================
    // Api side                                                                                 =
    //===========================================================================================
    // main menu                                            =
    //=======================================================
    //getting only the homework that are not in the local database
    private fun getHomeworkByIdListFromApi() {
        viewModelScope.launch {
            //getting the last homework id in the local database
            //so only the homework with id > from the last saved homework id will be fetched
            getLastHomeworkId()
            //getting the number of the new homework in the online database
            var numOfHomework: Int =
                studentRepository.getHomeworkByIdFromApi(
                    student.value!!.studentClass,
                    lastHomeworkId.value!!
                ).body()
                    ?.homeworks
                    ?.count()
                    ?: -1
            while (numOfHomework > 0) {
                val apiHomework: Homework
                //mapping api homework to local homework
                studentRepository.getHomeworkByIdFromApi(
                    student.value!!.studentClass,
                    lastHomeworkId.value!!
                ).body()!!
                    .homeworks[numOfHomework - 1]
                    .let {
                        apiHomework = Homework(
                            homeworkId = it.homeworkId,
                            homeworkTeacherId = it.homeworkTeacherId,
                            homeworkTeacherClass = it.homeworkTeacherClass,
                            homeworkTeacherSubject = it.homeworkTeacherSubject,
                            homeworkDetails = it.homeworkDetails,
                            homeworkStartDate = it.homeworkStartDate.toString(),
                            homeworkEndDate = it.homeworkEndDate.toString(),
                            homeworkStartDay = it.homeworkStartDay,
                            homeworkEndDay = it.homeworkEndDay,
                            homeworkIsComplete = it.homeworkIsCompleted,
                            homeworkFilePath = it.homeworkFilePath
                        )
                    }
                //inserting every homework to the local database
                insertHomework(apiHomework)
                numOfHomework--
            }
            //get all the homework in the local database.
            //usage: main menu lazy row
            getHomeworkList()
            //loading state complete
            _loadingState.value = HomeworkLoadingState.Completed
        }
    }

    //handle the insert for new homework for first time
    private fun insertHomeworkListFromApi() {
        viewModelScope.launch {
            //initializing the value of localHomeworkList.
            //Which will be all the homework are currently saved locally.
            getAllHomeworkList()
            //if the app's database doesn't have any homework fetch all the homework from the api
            if (localHomeworkList.value!!.isEmpty()) {
                //getting the number of the homework in the online database
                var numOfHomework: Int =
                    studentRepository.getHomeworkFromApi(student.value!!.studentClass)
                        .body()
                        ?.homeworks
                        ?.count()
                        ?: -1
                //fetch all the homework until last ine
                while (numOfHomework > 0) {
                    val apiHomework: Homework
                    //mapping api homework to local homework
                    studentRepository.getHomeworkFromApi(student.value!!.studentClass)
                        .body()!!
                        .homeworks[numOfHomework - 1]
                        .let {
                            apiHomework = Homework(
                                homeworkId = it.homeworkId,
                                homeworkTeacherId = it.homeworkTeacherId,
                                homeworkTeacherClass = it.homeworkTeacherClass,
                                homeworkTeacherSubject = it.homeworkTeacherSubject,
                                homeworkDetails = it.homeworkDetails,
                                homeworkStartDate = it.homeworkStartDate.toString(),
                                homeworkEndDate = it.homeworkEndDate.toString(),
                                homeworkStartDay = it.homeworkStartDay,
                                homeworkEndDay = it.homeworkEndDay,
                                homeworkIsComplete = it.homeworkIsCompleted,
                                homeworkFilePath = it.homeworkFilePath
                            )
                        }
                    //inserting every homework to the local database
                    insertHomework(apiHomework)
                    numOfHomework--
                }
                //get all the homework in the local database.
                //usage: main menu lazy row
                getHomeworkList()
                //complete loading state after getting homework
                _loadingState.value = HomeworkLoadingState.Completed
            } else {
                //update loading state to checking new homework
                _loadingState.value = HomeworkLoadingState.CheckingNewHomework
                //getting only the homework that are not in the local database
                getHomeworkByIdListFromApi()
            }
        }
    }

    //comparing student data class with the local database.
    //usage: if new student with different class signed in then delete all the homework.
    //and insert new ones
    private fun compareHomeworkByClass() {
        viewModelScope.launch {
            //start loading state
            _loadingState.value = HomeworkLoadingState.CheckingHomework
            //get the last homework class
            getLastHomeworkClass()
            if (student.value!!.studentClass != lastHomeworkClass.value) {
                deleteAllHomework()
                //update loading state to fetching
                _loadingState.value = HomeworkLoadingState.FetchingHomework
                //insert new homework from API
                insertHomeworkListFromApi()
            } else {
                //update loading state to fetching
                _loadingState.value = HomeworkLoadingState.FetchingHomework
                //insert new homework from API
                insertHomeworkListFromApi()
            }
        }
    }

    //calling the compare function
    fun checkHomework() {
        compareHomeworkByClass()
    }

    //=======================================================
    // profile page                                         =
    //=======================================================
    private suspend fun getParentFromApi() {
            //delete existing parent in case the parent doesn't represent the student
            deleteParent()
            val parent: Parent
            //assign the parent
            studentRepository.getParentFromApi(student.value!!.studentNationalId)
                .body()
                ?.parent
                .let {
                    parent = Parent(
                        parentId = 0,
                        parentPhoneNumber = it?.parentPhoneNumber ?: 0,
                        parentJob = it?.parentJob ?: "",
                        parentNationality = it?.parentNationality ?: "",
                        parentAddress = it?.parentAddress ?: ""
                    )
                }
            //insert in local database
            insertParent(parent)
    }

    init {
        viewModelScope.launch {
            setStudent()
            getParentFromApi()
            setParent()
        }

    }

    //===========================================================================================
    //functions: public & private                                                               =
    //===========================================================================================
    // main menu                                            =
    //=======================================================
    fun uploadFileToFirebase(fileUri: Uri) {
        val storageRef = FirebaseStorage.getInstance().reference
        val riversRef =
            storageRef.child("uploads/${fileUri.lastPathSegment}") // Customize the path as needed

        val uploadTask = riversRef.putFile(fileUri)
        uploadTask.addOnFailureListener {
            Log.e("FirebaseUpload", "Upload failed: ${it.message}")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d("FirebaseUpload", "Upload succeeded. Metadata: ${taskSnapshot.metadata}")
        }
    }

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

    fun showAlertDialog(index: Int, newState: Boolean) {
        if (index in _Settingstate.value.showAlertDialog.indices) {
            _Settingstate.value.showAlertDialog[index] = newState
        }
    }

    /*    //counselor Page
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
    //    }*/

    //ResourcesPage
    private val _Resourcesstate = MutableStateFlow(MainDataClass.ResourcesPageState())
    val Resourcesstate: StateFlow<MainDataClass.ResourcesPageState> = _Resourcesstate.asStateFlow()
    fun updateBottomSheetState2(index: Int, newState: Boolean) {
        // Ensure index is within bounds
        if (index in _Resourcesstate.value.BottomSheet1.indices) {
            _Resourcesstate.value.BottomSheet1[index] = newState
        }
    }

    fun isTopAppbarVisible5() {
        _Classesstate.value =
            MainDataClass.ClassesPageState(isTopAppBarVisible = !_Classesstate.value.isTopAppBarVisible)
    }
}