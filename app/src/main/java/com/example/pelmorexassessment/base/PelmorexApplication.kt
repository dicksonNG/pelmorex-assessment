package com.example.pelmorexassessment.base

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PelmorexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("DicksonDebug","DicksonDe bugeck st!")
    }
}