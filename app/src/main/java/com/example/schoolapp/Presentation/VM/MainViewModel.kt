package com.example.schoolapp.Presentation.VM

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.Presentation.VM.States.CalenderLoadingState
import com.example.schoolapp.Presentation.VM.States.CalenderState
import com.example.schoolapp.Presentation.VM.States.ExamLoadingState
import com.example.schoolapp.Presentation.VM.States.HomeworkLoadingState
import com.example.schoolapp.Presentation.VM.States.MainDataClass
import com.example.schoolapp.Presentation.VM.States.MarksLoadingState
import com.example.schoolapp.Presentation.VM.States.SessionLoadingState
import com.example.schoolapp.Presentation.VM.States.SessionState
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.CalenderEvent
import com.example.schoolapp.datasource.local.entity.Event
import com.example.schoolapp.datasource.local.entity.Exam
import com.example.schoolapp.datasource.local.entity.Homework
import com.example.schoolapp.datasource.local.entity.Mark
import com.example.schoolapp.datasource.local.entity.Parent
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.model.HomeworkResponseModel
import com.example.schoolapp.datasource.repository.StudentRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

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

    private val _homeworkResponses = MutableStateFlow<List<HomeworkResponseModel>?>(null)
    val homeworkResponses: StateFlow<List<HomeworkResponseModel>?> = _homeworkResponses.asStateFlow()


    //handle the homework loading state operations
    private val _loadingState = MutableStateFlow<HomeworkLoadingState>(HomeworkLoadingState.Initial)
    val loadingState: StateFlow<HomeworkLoadingState> = _loadingState.asStateFlow()

    //=======================================================
    //profile                                               =
    //=======================================================
    //handle parent operations
    private val _parent = MutableStateFlow<Parent?>(null) // Initialize with null
    val parent: StateFlow<Parent?> = _parent.asStateFlow()

    //=======================================================
    // calender page                                        =
    //=======================================================
    //calender object handles ROOM operations for Calender
    private val _calenderEventList =
        MutableStateFlow<List<CalenderEvent>?>(null) // Initialize with null
    val calenderEventList: StateFlow<List<CalenderEvent>?> = _calenderEventList.asStateFlow()

    //event object handles ROOM operations for Calender
    private val _eventList = MutableStateFlow<List<Event>?>(null) // Initialize with null
    val eventList: StateFlow<List<Event>?> = _eventList.asStateFlow()

    //calender object handles operations for Calender
    private val _calenderState = MutableStateFlow<CalenderState?>(null) // Initialize with null
    val calenderState: StateFlow<CalenderState?> = _calenderState.asStateFlow()

    //event object handles ROOM operations for Calender
    private val _event = MutableStateFlow<Event?>(null) // Initialize with null
    val event: StateFlow<Event?> = _event.asStateFlow()

    //calender event object handles ROOM operations for Calender
    private val _calenderEvent = MutableStateFlow<CalenderEvent?>(null) // Initialize with null
    val calenderEvent: StateFlow<CalenderEvent?> = _calenderEvent.asStateFlow()

    private val _calenderLoadingState =
        MutableStateFlow<CalenderLoadingState>(CalenderLoadingState.Initial)
    val calenderLoadingState: StateFlow<CalenderLoadingState> = _calenderLoadingState.asStateFlow()

    //=======================================================
    // Exam page                                            =
    //=======================================================
    //exam object handles ROOM operations for Calender
    private val _examList = MutableStateFlow<List<Exam>?>(null) // Initialize with null
    val examList: StateFlow<List<Exam>?> = _examList.asStateFlow()

    //exam loading state
    private val _examLoadingState = MutableStateFlow<ExamLoadingState>(ExamLoadingState.Initial)
    val examLoadingState: StateFlow<ExamLoadingState> = _examLoadingState.asStateFlow()

    //exam object handles ROOM operations for Calender
    private val _examCompareList = MutableStateFlow<List<Exam>?>(null) // Initialize with null
    val examCompareList: StateFlow<List<Exam>?> = _examCompareList.asStateFlow()

    // Add a new state to track initial data load
    private val _isInitialLoad = MutableStateFlow(true)
    val isInitialLoad: StateFlow<Boolean> = _isInitialLoad.asStateFlow()

    //=======================================================
    // sessions page                                        =
    //=======================================================
    private val _sessionState = MutableStateFlow(SessionState())
    val sessionState: StateFlow<SessionState> = _sessionState.asStateFlow()

    private val _sessionLoadingState =
        MutableStateFlow<SessionLoadingState>(SessionLoadingState.Initial)
    val sessionLoadingState: StateFlow<SessionLoadingState> = _sessionLoadingState.asStateFlow()

    //handle teacher name for sessions
    private val _teacherName = MutableStateFlow<String?>(null)
    val teacherName: StateFlow<String?> = _teacherName.asStateFlow()

    //============================================================================================
    // Marks page                                                                                =
    //============================================================================================
    private val _marksList = MutableStateFlow<List<Mark>?>(null)
    val marksList: StateFlow<List<Mark>?> = _marksList.asStateFlow()

    private val _marksLoadingState = MutableStateFlow<MarksLoadingState>(MarksLoadingState.Initial)
    val marksLoadingState: StateFlow<MarksLoadingState> = _marksLoadingState.asStateFlow()


    //Exam Page
    private val _Examstate = MutableStateFlow(MainDataClass.ExamPageState1())
    val Examstate: StateFlow<MainDataClass.ExamPageState1> = _Examstate.asStateFlow()

    //Marks Page
    private val _Marksstate = MutableStateFlow(MainDataClass.MarksPageState1())
    val Marksstate: StateFlow<MainDataClass.MarksPageState1> = _Marksstate.asStateFlow()

    //setting page
//    private val _settingstate = MutableStateFlow<setting?>(null) // Initialize with null
//    val settingstate: StateFlow<setting?> = _settingstate.asStateFlow()
//    fun showAlertDialog(index: Int, show: Boolean) {
//        _settingstate.update { currentState ->
//            val newDialogStates = currentState.showAlertDialog.toMutableList()
//            newDialogStates[index] = show
//            currentState.copy(showAlertDialog = newDialogStates)
//        }
//    }
    data class SettingsState(
        val showAlertDialog: List<Boolean> = List(4) { false }  // Initialize with 4 false values
    )

    private val _settingState = MutableStateFlow(SettingsState())

    // Public immutable state
    val Settingstate = _settingState.asStateFlow()

    fun showAlertDialog(index: Int, show: Boolean) {
        _settingState.update { currentState ->
            val newDialogStates = currentState.showAlertDialog.toMutableList()
            if (index in newDialogStates.indices) {  // Add bounds check
                newDialogStates[index] = show
            }
            currentState.copy(showAlertDialog = newDialogStates)
        }
    }


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

    //=======================================================
    // Event ROOM functions                                  =
    //=======================================================
    private suspend fun insertEvent(event: Event) {
        studentRepository.insertEvent(event)
    }

    private suspend fun deleteAllEvents() {
        studentRepository.deleteAllEvents()
    }

    private suspend fun getAllEventList() {
        _eventList.value = studentRepository.getAllEvents()
    }

    private suspend fun getEvent(id: Int) {
        _event.value = studentRepository.getEvent(id)
    }

    //=======================================================
    // Calendar Event ROOM functions                        =
    //=======================================================
    private suspend fun insertCalenderEvent(calenderEvent: CalenderEvent) {
        studentRepository.insertCalenderEvent(calenderEvent)
    }

    private suspend fun deleteAllCalenderEvents() {
        studentRepository.deleteAllCalenderEvents()
    }

    private suspend fun getAllCalenderEvents() {
        _calenderEventList.value = studentRepository.getCalenderEvents()
    }

    private suspend fun getCalenderEvent(id: Int) {
        _calenderEvent.value = studentRepository.getCalenderEvent(id)
    }

    //=======================================================
    // Exam ROOM functions                                  =
    //=======================================================
    private suspend fun insertExam(exam: Exam) {
        studentRepository.insertExam(exam)
    }

    private suspend fun deleteAllExams() {
        studentRepository.deleteAllExams()
    }

    private suspend fun getAllExamList() {
        _examCompareList.value = studentRepository.getExams()
    }

    private suspend fun getExamsByClass(studentClass: String) {
        _examList.value = studentRepository.getExamsByClass(studentClass)
    }

    private suspend fun getNewExamList() {
        Log.d("ExamsDebug", "Getting new exam list")
        // Get exams with dates after current date
        _examList.value = studentRepository.getNewExams()
        Log.d("ExamsDebug", "Updated exam list size: ${_examList.value?.size}")
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
                            homeworkEndDay = it.homeworkEndDay
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
                                homeworkEndDay = it.homeworkEndDay
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

    fun fetchHomeworkResponses(studentId: Int) {
        viewModelScope.launch {
            try {
                //here: Unresolved reference: getHomeworkResponses
                val response = studentRepository.getHomeworkResponses(studentId)
                if (response.isSuccessful) {
                    _homeworkResponses.value = response.body()?.responses
                }
            } catch (e: Exception) {
                Log.e("Homework", "Error fetching homework responses", e)
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

    // Add these functions to MainViewModel.kt

    fun submitHomeworkResponse(homeworkId: Int, studentId: Int, filePath: String) {
        viewModelScope.launch {
            try {
                val response = studentRepository.submitHomeworkResponse(homeworkId, studentId, filePath)
                if (response.isSuccessful && !response.body()?.error!!) {
                    // Refresh homework list to reflect changes
                    getHomeworkList()
                }
            } catch (e: Exception) {
                Log.e("HomeworkResponse", "Error submitting homework response", e)
            }
        }
    }

    //=======================================================
    // profile page                                         =
    //=======================================================
    private suspend fun getParentFromApi() {
        //delete existing parent in case the parent doesn't represent the student
        deleteParent()
        val theParent: Parent
        //assign the parent
        studentRepository.getParentFromApi(student.value!!.studentId)
            .body()!!
            .parent
            .let {
                theParent = Parent(
                    parentId = 0,
                    parentPhoneNumber = it.parentPhoneNumber,
                    parentJob = it.parentJob,
                    parentNationality = it.parentNationality,
                    parentAddress = it.parentAddress
                )
            }
        //insert in local database
        insertParent(theParent)
    }

    init {
        viewModelScope.launch {
            setStudent()
            getParentFromApi()
            setParent()
        }
    }

    //=======================================================
    // calender page                                        =
    //=======================================================
    //calender Event
    private suspend fun getCalenderEventsFromApi() {
        var calenderEvent: CalenderEvent
        // Getting the list of events from the API
        val calenderEvents = studentRepository.getCalenderFromApi()
            .body()
            ?.calenderSemesterEvents
            ?: emptyList() // Ensure the list is not null

        // Iterate through the list in reverse order
        for (index in calenderEvents.indices.reversed()) {
            calenderEvents[index].let {
                calenderEvent = CalenderEvent(
                    eventId = it.eventId,
                    eventDescription = it.eventDescription,
                    eventStartDate = it.eventStartDate.toString(),
                    eventEndDate = it.eventEndDate.toString(),
                    eventStartDay = it.eventStartDay
                )
            }
            // Insert into local database
            insertCalenderEvent(calenderEvent)
        }

        getAllCalenderEvents()
    }

    fun compareCalender() {
        viewModelScope.launch {
            // Start loading state
            _calenderLoadingState.value = CalenderLoadingState.CheckingCalender

            val onlineEvent = studentRepository.getCalenderFromApi()
                .body()!!
                .calenderSemesterEvents

            getAllCalenderEvents() // Get current local events
            val localEventNum = calenderEventList.value?.count() ?: 0

            if (localEventNum == 0) {
                // If no local events, fetch all
                _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                getCalenderEventsFromApi()
                _calenderLoadingState.value = CalenderLoadingState.Completed
            } else {
                // Check if we need to update
                _calenderLoadingState.value = CalenderLoadingState.CheckingNewCalender

                val localEvent = studentRepository.getCalenderEvent(onlineEvent[0].eventId)
                val isSameDescription =
                    onlineEvent[0].eventDescription == localEvent?.eventDescription
                val isSameStartDate =
                    onlineEvent[0].eventStartDate.toString() == localEvent?.eventStartDate
                val isSameEndDate =
                    onlineEvent[0].eventEndDate.toString() == localEvent?.eventEndDate
                val isSameStartDay = onlineEvent[0].eventStartDay == localEvent?.eventStartDay

                if (!isSameDescription || !isSameStartDate || !isSameEndDate || !isSameStartDay) {
                    // Data is different, update required
                    _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                    deleteAllCalenderEvents()
                    getCalenderEventsFromApi()
                } else if (onlineEvent.count() != localEventNum) {
                    // Number of events different, update required
                    _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                    getCalenderEventsFromApi()
                }

                _calenderLoadingState.value = CalenderLoadingState.Completed
            }
        }
    }

    //events
    private suspend fun getEventsFromApi() {
        var event: Event
        // Getting the list of events from the API for the student's class
        val events = studentRepository.getCalenderEventsFromApi(student.value!!.studentClass)
            .body()
            ?.calenderEvents
            ?: emptyList() // Ensure the list is not null

        // Iterate through the list in reverse order (similar to calendar events)
        for (index in events.indices.reversed()) {
            events[index].let {
                event = Event(
                    eventId = it.eventId,
                    eventName = it.eventName,
                    eventDetails = it.eventDetails,
                    eventStartDate = it.eventStartDate,
                    eventStartDay = it.eventStartDay,
                    eventEndDate = it.eventEndDate,
                    eventEndDay = it.eventEndDay,
                    eventClass = it.eventClass
                )
            }
            // Insert into local database
            insertEvent(event)
        }

        // Update the event list in the ViewModel
        getAllEventList()
    }

    fun compareEvents() {
        viewModelScope.launch {
            // Start loading state
            _calenderLoadingState.value = CalenderLoadingState.CheckingCalender

            // Get local events first
            getAllEventList()

            // Get online events
            val onlineEvents =
                studentRepository.getCalenderEventsFromApi(student.value!!.studentClass)
                    .body()
                    ?.calenderEvents
                    ?: emptyList()
            val localEventNum = eventList.value?.count() ?: 0

            if (localEventNum == 0) {
                // If no local events, fetch all
                _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                getEventsFromApi()
                _calenderLoadingState.value = CalenderLoadingState.Completed
            } else {
                // Check if we need to update
                _calenderLoadingState.value = CalenderLoadingState.CheckingNewCalender

                // Get the first event to compare class
                val localEvent = eventList.value?.firstOrNull()

                // Check if the event class matches student class
                if (localEvent?.eventClass != student.value?.studentClass) {
                    // Class mismatch - need to refresh all events
                    _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                    deleteAllEvents()
                    getEventsFromApi()
                } else if (onlineEvents.isNotEmpty()) {
                    // Class matches, check if we need to update based on content
                    val firstOnlineEvent = onlineEvents[0]
                    val isSameDetails = firstOnlineEvent.eventDetails == localEvent?.eventDetails
                    val isSameStartDate =
                        firstOnlineEvent.eventStartDate.toString() == localEvent?.eventStartDate
                    val isSameEndDate =
                        firstOnlineEvent.eventEndDate.toString() == localEvent?.eventEndDate

                    if (!isSameDetails || !isSameStartDate || !isSameEndDate || onlineEvents.size != localEventNum) {
                        // Data is different or number of events different, update required
                        _calenderLoadingState.value = CalenderLoadingState.FetchingCalender
                        deleteAllEvents()
                        getEventsFromApi()
                    }
                }

                _calenderLoadingState.value = CalenderLoadingState.Completed
            }
        }
    }

    //=======================================================
    //exam page                                             =
    //=======================================================
    private suspend fun getExamsFromApi() {
        Log.d("ExamsDebug", "Starting getExamsFromApi")
        try {
            // Getting the list of exams from the API for the student's class
            val exams = withContext(Dispatchers.IO) {
                studentRepository.getExamCalenderFromApi(student.value!!.studentClass)
                    .body()?.exams ?: emptyList()
            }

            Log.d("ExamsDebug", "API returned ${exams.size} exams")

            // Process exams in background
            withContext(Dispatchers.IO) {
                // Delete existing exams first to avoid duplicates
                deleteAllExams()

                // Insert all exams
                exams.forEach { exam ->
                    insertExam(
                        Exam(
                            examId = exam.examId,
                            examTeacherId = exam.examTeacherId,
                            examTeacherSubject = exam.examTeacherSubject,
                            examTeacherClass = exam.examTeacherClass,
                            examDate = exam.examDate.toString(),
                            examDay = exam.examDay,
                            examMaterial = exam.examMaterial,
                            examNotes = exam.examNotes
                        )
                    )
                }
            }

            // Update the exam list in the ViewModel
            getNewExamList()
            Log.d("ExamsDebug", "Final exam list size: ${_examList.value?.size}")
        } catch (e: Exception) {
            Log.e("ExamsDebug", "Error in getExamsFromApi", e)
            throw e
        }
    }

    fun compareExams() {
        viewModelScope.launch {
            // Don't show loading state if we already have data
            if (_examList.value == null) {
                _examLoadingState.value = ExamLoadingState.Checking
            }

            try {
                // First try to load cached data immediately
                getAllExamList()
                if (!_examList.value.isNullOrEmpty()) {
                    _examLoadingState.value = ExamLoadingState.Completed
                }

                // Then check for updates
                val onlineExams =
                    studentRepository.getExamCalenderFromApi(student.value!!.studentClass)
                        .body()
                        ?.exams
                        ?: emptyList()

                val localExamNum = examCompareList.value?.count() ?: 0

                if (localExamNum == 0 || _isInitialLoad.value) {
                    _examLoadingState.value = ExamLoadingState.Fetching
                    getExamsFromApi()
                    _isInitialLoad.value = false
                } else {
                    // Check if we need to update
                    _examLoadingState.value = ExamLoadingState.CheckingNew
                    // Get the first exam to compare class
                    val localExam = examCompareList.value?.firstOrNull()
                    // Check if the exam class matches student class
                    if (localExam?.examTeacherClass != student.value?.studentClass) {
                        _examLoadingState.value = ExamLoadingState.Fetching
                        deleteAllExams()
                        getExamsFromApi()
                    } else if (onlineExams.isNotEmpty()) {
                        if (onlineExams.size != localExamNum) {
                            _examLoadingState.value = ExamLoadingState.Fetching
                            deleteAllExams()
                            getExamsFromApi()
                        }
                    }
                }
                _examLoadingState.value = ExamLoadingState.Completed
            } catch (e: Exception) {
                Log.e("ExamsDebug", "Error in compareExams", e)
                _examLoadingState.value = ExamLoadingState.Error(e.message ?: "Unknown error")
            }
        }
    }

    //=======================================================
    // session page api                                     =
    //=======================================================
    fun setCurrentDay(day: String) {
        viewModelScope.launch {
            _sessionLoadingState.value = SessionLoadingState.LoadingData
            _sessionState.value = _sessionState.value.copy(currentDay = day)
            fetchSessionsForDay(day)
        }
    }

    private suspend fun fetchSessionsForDay(day: String) {
        try {
            val response = studentRepository.getSessionsFromApi(student.value?.studentClass ?: "")
            Log.d("SessionDebug", "API Response: ${response.body()}")

            if (response.isSuccessful && response.body() != null) {
                val daySessions = response.body()!!.sessions
                    .also { Log.d("SessionDebug", "All sessions: $it") }
                    .filter { it.day.lowercase() == day.lowercase() }  // Make case-insensitive comparison
                    .also { Log.d("SessionDebug", "Filtered sessions for $day: $it") }
                    .sortedBy {
                        when (it.session.lowercase()) {
                            "first" -> 1
                            "second" -> 2
                            "third" -> 3
                            "fourth" -> 4
                            "fifth" -> 5
                            "sixth" -> 6
                            "seventh" -> 7
                            else -> 8
                        }
                    }

                _sessionState.value = _sessionState.value.copy(
                    sessions = daySessions
                )
                Log.d("SessionDebug", "Final state sessions: ${_sessionState.value.sessions}")
                _sessionLoadingState.value = SessionLoadingState.Completed
            } else {
                Log.e("SessionDebug", "API Error: ${response.errorBody()?.string()}")
                _sessionLoadingState.value = SessionLoadingState.Error("Failed to fetch sessions")
            }
        } catch (e: Exception) {
            Log.e("SessionDebug", "Exception: ${e.message}", e)
            _sessionLoadingState.value = SessionLoadingState.Error(e.message ?: "Unknown error")
        }
    }

    // Initial fetch - can be called when screen is first loaded
    fun initializeSessions() {
        viewModelScope.launch {
            val initialDay = "Sunday" // Or whatever day you want to start with
            _sessionState.value = _sessionState.value.copy(currentDay = initialDay)
            fetchSessionsForDay(initialDay)
        }
    }

    //get teacher name from API
    private suspend fun getTeacherNameFromApi(teacherId: Int) {
        studentRepository.getTeacherFromApi(teacherId)
            .body()?.teacher?.let { teacher ->
                _teacherName.value = teacher.teacherFirstName
            }
    }

    //function to fetch teacher name that can be called from UI
    fun fetchTeacherName(teacherId: Int) {
        viewModelScope.launch {
            getTeacherNameFromApi(teacherId)
        }
    }

    //============================================================================================
    // Marks page                                                                                =
    //============================================================================================
    // Check and sync marks data
    // In MainViewModel.kt
    fun checkMarks() {
        viewModelScope.launch {
            try {
                _marksLoadingState.value = MarksLoadingState.Checking

                // Get current student ID
                val currentStudent = student.value
                if (currentStudent == null) {
                    Log.e("MarksDebug", "Student is null")
                    _marksLoadingState.value = MarksLoadingState.Error("Student data not available")
                    return@launch
                }

                Log.d("MarksDebug", "Current student ID: ${currentStudent.studentId}")
                _marksLoadingState.value = MarksLoadingState.Loading

                // Fetch and store marks from API
                studentRepository.getMarksWithSync(currentStudent.studentId)

                // Get marks from local database
                _marksList.value = studentRepository.getMarks(currentStudent.studentId)

                _marksLoadingState.value = MarksLoadingState.Completed
            } catch (e: Exception) {
                Log.e("MarksDebug", "Error checking marks", e)
                _marksLoadingState.value =
                    MarksLoadingState.Error(e.message ?: "Unknown error occurred")
            }
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
        try {
            // Ensure we have enough space in the array
            while (index >= _Examstate.value.bottomSheet.size) {
                _Examstate.value.bottomSheet.add(false)
            }
            // Now safely update the state
            _Examstate.value.bottomSheet[index] = newState
        } catch (e: Exception) {
            Log.e("ExamsDebug", "Error updating bottom sheet state", e)
        }
    }

    fun updateBottomSheetState1(index: Int, newState: Boolean) {
        try {
            // Make sure we have enough space in the list
            while (index >= _Marksstate.value.bottomSheet.size) {
                _Marksstate.value.bottomSheet.add(false)
            }
            // Now safely update the state
            _Marksstate.value.bottomSheet[index] = newState
        } catch (e: Exception) {
            Log.e("MarksPage", "Error updating bottom sheet state", e)
        }
    }

    private val _Counselorstate = MutableStateFlow(MainDataClass.CounselorState())
    val Counselorstate: StateFlow<MainDataClass.CounselorState> = _Counselorstate.asStateFlow()

    fun updateName(name: String) {
        _Counselorstate.value = _Counselorstate.value.copy(name = name)
    }

    fun updateDate(date: LocalDate) {
        _Counselorstate.value = _Counselorstate.value.copy(selectedDate = date)
    }

    fun updateTime(time: String) {
        _Counselorstate.value = _Counselorstate.value.copy(time = time)
    }

    fun toggleContactDialog() {
        _Counselorstate.value =
            _Counselorstate.value.copy(showContactDialog = !_Counselorstate.value.showContactDialog)
    }

    fun submitRequest() {
        // Implement submission logic here
        _Counselorstate.value = _Counselorstate.value.copy(isLoading = true)
    }


    //ResourcesPage
    private val _Resourcesstate = MutableStateFlow(MainDataClass.ResourcesPageState())
    val Resourcesstate: StateFlow<MainDataClass.ResourcesPageState> =
        _Resourcesstate.asStateFlow()

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