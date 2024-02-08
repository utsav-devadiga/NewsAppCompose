package com.applabs.newsappcompose.ui.viewmodel

import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applabs.newsappcompose.data.AppConstants
import com.applabs.newsappcompose.data.entity.NewsResponse
import com.applabs.newsappcompose.ui.repository.NewsRepository
import com.applabs.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private val _news: MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())

    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstants.COUNTRY)
    }

    private fun getNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadline(country)
                .collectLatest { newsResponse ->
                    _news.value = newsResponse
                }
        }
    }


    companion object {
        const val TAG = "NEWS_VIEW_MODEL"
    }
}