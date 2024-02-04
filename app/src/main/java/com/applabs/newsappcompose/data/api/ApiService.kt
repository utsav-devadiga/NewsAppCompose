package com.applabs.newsappcompose.data.api

import com.applabs.newsappcompose.data.AppConstants
import com.applabs.newsappcompose.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = AppConstants.API_KEY
    ): Response<NewsResponse>
}