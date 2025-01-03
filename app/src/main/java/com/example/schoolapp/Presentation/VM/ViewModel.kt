package com.example.schoolapp.Presentation.VM

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.Presentation.Util.callbacks.DatabaseCallback
import com.example.schoolapp.Presentation.Util.callbacks.SignInCallBack
import com.example.schoolapp.Presentation.VM.States.SignInPageState
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.response.StudentResponse
import com.example.schoolapp.datasource.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//todo @LT #simple|| please add usage of this file
class AppViewModel(private val context: Context) : ViewModel() {
    //=======================================================
    //Repository: Student                                   =
    //=======================================================
    private val studentRepository = StudentRepository(
        StudentDatabase.getInstance(context)
    )

    //=======================================================
    //variables: local & states                             =
    //=======================================================
    // Loading state
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    //signIn data get data from user (UI)
    private val _signInState = MutableStateFlow(SignInPageState())
    val signInState: StateFlow<SignInPageState> = _signInState.asStateFlow()

    //student response object holds the API response
    private val _studentResponse = MutableStateFlow<StudentResponse?>(null) // Initialize with null
    val studentResponse: StateFlow<StudentResponse?> = _studentResponse.asStateFlow()

    //student object handles ROOM operations
    private val _student = MutableStateFlow<Student?>(null) // Initialize with null
    val student: StateFlow<Student?> = _student.asStateFlow()

    //student flag handle sign-in status
    private val _studentFlag = MutableStateFlow(false) // Initialize with null
    val studentFlag: StateFlow<Boolean> = _studentFlag.asStateFlow()


    //=======================================================
    // ROOM side                                            =
    //=======================================================
    private fun insertStudent(student: Student, databaseCallback: DatabaseCallback) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                studentRepository.insertStudent(student)
                databaseCallback.onSuccess("Student inserted successfully")
            } catch (e: Exception) {
                databaseCallback.onFailure(e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun insertApiStudentToLocalDatabase() {
        if (!studentResponse.value!!.error) {
            // First, delete any existing student
            viewModelScope.launch {
                try {
                    // Get the student ID before deletion for logging
                    val existingStudent = studentRepository.setStudent()
                    existingStudent?.let {
                        studentRepository.deleteStudent(it.studentId)
                    }

                    // Now map and insert the new student with their actual ID
                    studentResponse.value!!.student!!.let {
                        _student.value = Student(
                            studentId = it.studentId,  // Use actual ID from API
                            studentUsername = it.studentUsername,
                            studentPassword = it.studentPassword,
                            studentClass = it.studentClass,
                            studentStatus = it.studentStatus,
                            studentGender = it.studentGender,
                            studentFirstName = it.studentFirstName,
                            studentSecondName = it.studentSecondName,
                            studentThirdName = it.studentThirdName,
                            studentNationalId = it.studentNationalId,
                            studentDateOfRegistration = it.studentDateOfRegistration.toString(),
                            studentDateOfBirth = it.studentDateOfBirth.toString(),
                            studentPlaceOfBirth = it.studentPlaceOfBirth,
                            studentCity = it.studentCity,
                            studentResidence = it.studentResidence,
                            studentNationality = it.studentNationality,
                            studentNotes = it.studentNotes,
                            studentProfileImage = it.studentProfileImage,
                            imageFlag = it.imageFlag
                        )
                    }

                    insertStudent(student.value!!, object : DatabaseCallback {
                        override fun onSuccess(message: String) {
                            _studentFlag.value = true
                        }

                        override fun onFailure(error: String) {
                            _studentFlag.value = false
                        }
                    })
                } catch (e: Exception) {
                    Log.e("AppViewModel", "Error managing student data", e)
                    _studentFlag.value = false
                }
            }
        }
    }

    //=======================================================
    // Api side                                             =
    //=======================================================
    fun signInFromApi(signInCallBack: SignInCallBack) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _studentResponse.value =
                    studentRepository.getStudentFromApi(signInState.value.userName).body()
                signInCallBack.onSuccess(studentResponse.value?.message.toString())
            } catch (e: Exception) {
                signInCallBack.onFailure(studentResponse.value?.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    //=======================================================
    //functions: private & public                           =
    //=======================================================
    fun onDialog(value: Boolean, message: String = "") {
        _signInState.update { it.copy(alertDialog = value, message = message) }
    }

    fun reset(){
        _signInState.update {
            it.copy(
                userName = "",
                password = "",
                isPasswordVisible = false,
                alertDialog = false,
                message = ""
            )
        }
    }

    //dumb i know but i am not touching it :) if it works it works:))))
    fun removeDialog() {
        _signInState.update { it.copy(alertDialog = false, message = "") }
    }

    fun onUserNameChange(userName: String) {
        _signInState.update { it.copy(userName = userName) }
    }

    fun onPasswordChange(password: String) {
        _signInState.update { it.copy(password = password) }
    }

    fun onPasswordVisibilityChange() {
        _signInState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun notifyMessage(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }

    fun checkStudent(context: Context): Boolean {
        //check if username exist and == to an actual student
        if (studentResponse.value!!.student!!.studentUsername == signInState.value.userName) {
            //check if password == to the student
            if (studentResponse.value!!.student!!.studentPassword == signInState.value.password) {
                return true
            }
            //password != to the student
            else {

                val studentFirstName = studentResponse.value!!.student!!.studentFirstName
                onDialog(true, "Wrong Password for \'$studentFirstName\' !!")
                return false
            }
        }
        //username exist and != to an actual student
        else {
            notifyMessage(context, "Wrong Username!!")
            return false
        }
    }

    fun startStudentFlagCheck(onClick: () -> Unit) {
        viewModelScope.launch {
            studentFlag.collect { flag ->
                if (flag) {
                    onClick()
                    return@collect
                }
            }
        }
    }
}