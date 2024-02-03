package com.applabs.newsappcompose.ui.viewmodel

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor() : ViewModel() {


    val value = "value"

    init {
        Log.d(TAG, "init block of NewsViewModel")
    }


    fun doSomething() {
        Log.d(TAG, "SOME WORK")
    }

    companion object {
        const val TAG = "NEWS_VIEW_MODEL"
    }
}