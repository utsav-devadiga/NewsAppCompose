package com.applabs.newsappcompose.ui.repository

import com.applabs.newsappcompose.data.datasource.NewsDataSource
import com.applabs.newsappcompose.data.entity.NewsResponse
import com.applabs.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadline(country)
            if (response.code() == 200 && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching Response: ${response.message()}"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }
}