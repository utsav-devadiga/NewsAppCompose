package com.applabs.newsappcompose

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "COMING INSIDE ONCREATE")
    }

    companion object {
        const val TAG = "NEWS_APPLICATION"
    }
}