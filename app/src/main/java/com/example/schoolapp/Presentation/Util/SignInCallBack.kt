package com.example.schoolapp.Presentation.Util

interface SignInCallBack {
    fun onSuccess(message: String)
    fun onFailure(error: String)
}
