package com.example.schoolapp.Presentation.VM.States

sealed class HomeworkLoadingState {
    //initial state before any operation
    object Initial : HomeworkLoadingState()
    //loading state while checking student class
    object CheckingHomework : HomeworkLoadingState()
    //loading state while getting homework from API
    object FetchingHomework : HomeworkLoadingState()
    //loading state while getting updated homework from API
    object CheckingNewHomework : HomeworkLoadingState()
    //final state when all operations are done
    object Completed : HomeworkLoadingState()
}