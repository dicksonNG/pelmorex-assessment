package com.example.pelmorexassessment.ui.contactus

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor() : ViewModel() {

    val emailRegex = ".+@.+\\.+[a-zA-Z]{2,}\$+"
    val phoneRegex = "^[0-9]{10}\$"
    val nameRegex = "^[^0-9]{4,}\$"

    val _email = MutableLiveData<String>()
    val _phone = MutableLiveData<String>()
    val _name = MutableLiveData<String>()

    val isAllFieldValid = MediatorLiveData<Boolean>().apply  {
        addSource(_email) { postValue(isFieldValid()) }
        addSource(_phone) { postValue(isFieldValid()) }
        addSource(_name) { postValue(isFieldValid()) }
    }

    fun isFieldValid(): Boolean {

        if (_name.value.isNullOrEmpty() || _email.value.isNullOrEmpty() || _phone.value.isNullOrEmpty()) return false
        if (!isValid(_name.value ?: "", nameRegex)) return false
        if (!isValid(_email.value ?: "", emailRegex)) return false
        if (!isValid(_phone.value ?: "", phoneRegex)) return false

        return true
    }

    fun setName(name: String) {
        _name.postValue(name)
    }

    fun setEmail(email: String) {
        _email.postValue(email)
    }

    fun setPhoneNumber(phone: String) {
        _phone.postValue(phone)
    }

    private fun isValid(input: String, regex: String): Boolean {
        return Pattern.compile(regex).matcher(input).matches()
    }


}