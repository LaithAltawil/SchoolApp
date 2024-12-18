package com.example.schoolapp.Presentation.Util.callbacks

interface DatabaseCallback {
    fun onSuccess(message: String)
    fun onFailure(error: String)
}