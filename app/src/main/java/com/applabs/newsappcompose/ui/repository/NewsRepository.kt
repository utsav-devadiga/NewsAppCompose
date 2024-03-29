package com.applabs.newsappcompose.ui.repository

import android.content.Context
import android.util.Log
import com.applabs.newsappcompose.NewsApplication
import com.applabs.newsappcompose.data.datasource.NewsDataSource
import com.applabs.newsappcompose.data.entity.NewsResponse
import com.applabs.utilities.CoreUtility
import com.applabs.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource,
    private val context: Context
) {
    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            if (CoreUtility.isInternetAvailable(context)) {
                val response = newsDataSource.getNewsHeadline(country)
                if (response.isSuccessful && response.body() != null) {
                    emit(ResourceState.Success(response.body()!!))
                } else {
                    emit(ResourceState.Error("Error fetching response!\nError code: ${response.code()}"))
                }
            } else {
                emit(ResourceState.Error("Internet is not available.\nPlease try again after the network is restored."))
            }

        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "OOPS!\nSomething went wrong."))
        }
    }
}