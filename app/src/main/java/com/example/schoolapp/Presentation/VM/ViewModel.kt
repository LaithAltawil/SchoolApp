package com.example.schoolapp.Presentation.VM

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolapp.Presentation.Util.SignInCallBack
import com.example.schoolapp.Presentation.VM.States.SignInPageState
import com.example.schoolapp.datasource.local.database.StudentDatabase
import com.example.schoolapp.datasource.local.entity.Student
import com.example.schoolapp.datasource.online.response.StudentResponse
import com.example.schoolapp.datasource.repository.StudentRepository
import kotlinx.coroutines.delay
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
    //signIn data get data from user (UI)
    private val _signInState = MutableStateFlow(SignInPageState())
    val signInState: StateFlow<SignInPageState> = _signInState.asStateFlow()

    //student response object holds the API response
    private val _studentResponse = MutableStateFlow<StudentResponse?>(null) // Initialize with null
    val studentResponse: StateFlow<StudentResponse?> = _studentResponse.asStateFlow()

    //student object handles ROOM operations
    private val _student = MutableStateFlow<Student?>(null) // Initialize with null
    val student: StateFlow<Student?> = _student.asStateFlow()

    //student object handles ROOM operations
    private val _localStudent = MutableStateFlow<Student?>(null) // Initialize with null
    val localStudent: StateFlow<Student?> = _student.asStateFlow()

    //=======================================================
    // ROOM side                                            =
    //=======================================================
    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.insertStudent(student)
        }
    }

    fun insertApiStudentToLocalDatabase() {
        if (!studentResponse.value!!.error) {
            studentResponse.value!!.student!!.let {
                _student.value = Student(
                    //always 0 cus I want only one student
                    studentId = 0,
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
                    studentProfileImage = it.studentProfileImage
                )
            }
            insertStudent(student.value!!)
        }
    }

    //=======================================================
    // Api side                                             =
    //=======================================================
    fun signInFromApi(signInCallBack: SignInCallBack) {
        viewModelScope.launch {
            try {
                _studentResponse.value =
                    studentRepository.getStudentFromApi(signInState.value.userName).body()
                signInCallBack.onSuccess(studentResponse.value?.message.toString())

            } catch (e: Exception) {
                signInCallBack.onFailure(studentResponse.value?.message.toString())
            }
        }
    }

    /*init {
        viewModelScope.launch {
            delay(5000)
        _studentResponse.value =
            studentRepository.getStudentFromApi(signInState.value.userName).body()
            Log.e("student", studentResponse.value!!.message)
    }
    }*/
    //=======================================================
    //functions: private & public                           =
    //=======================================================
    fun onUserNameChange(userName: String) {
        _signInState.update { it.copy(userName = userName) }
    }

    fun onPasswordChange(password: String) {
        _signInState.update { it.copy(password = password) }
    }

    fun onPasswordVisibilityChange() {
        _signInState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun notifyMessage(context: Context, message: String) {
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
                notifyMessage(context, "Wrong Password for \'$studentFirstName\' !!")
                return false
            }
        }
        //username exist and != to an actual student
        else {
            notifyMessage(context, "Wrong Username!!")
            return false
        }
    }

    /*fun onSignInButtonClick(view: View) {
       // Show loading dialog
       val loadingDialog = ProgressDialog(context)
       loadingDialog.setMessage("Signing in...")
       loadingDialog.setCancelable(false)
       loadingDialog.show()

       signInFromApi(object : SignInCallBack {
           override fun onSuccess(message: String) {
               // Dismiss loading dialog
               loadingDialog.dismiss()
               // Handle success
               if (checkStudent(context)) {
                   insertApiStudentToLocalDatabase()
               }
           }

           override fun onFailure(error: String) {
               // Dismiss loading dialog
               loadingDialog.dismiss()
               // Handle failure
               notifyMessage(
                   context,
                   error
               )
           }
       })
   }*/
}