package com.example.schoolapp

//for sign in with api logic
interface SignInStudentCallBack {
    fun onSuccess(message: String)
    fun onFailure(error: String)
}