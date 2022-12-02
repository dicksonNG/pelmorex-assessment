package com.example.pelmorexassessment.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val errorDialogAction = SingleLiveEvent<Error>()
    val showLoading = SingleLiveEvent<Boolean>()
}