package com.example.schoolapp.Presentation.Util.callbacks

interface SignInCallBack {
    fun onSuccess(message: String)
    fun onFailure(error: String)
}
