package com.example.schoolapp.Presentation.VM.States

data class ResourcesPageState(
    val isTopBarVisible: Boolean = false,
    val showBottomSheet: List<Boolean> = List(10) { false },

)
