package com.applabs.newsappcompose.data.datasource

import com.applabs.newsappcompose.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

   suspend fun getNewsHeadline(
        country: String,
    ): Response<NewsResponse>

}