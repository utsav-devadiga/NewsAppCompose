package com.applabs.newsappcompose.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.applabs.newsappcompose.ui.components.EmptyStateComponent
import com.applabs.newsappcompose.ui.components.ErrorStateComponent
import com.applabs.newsappcompose.ui.components.Loader
import com.applabs.newsappcompose.ui.components.NewsList
import com.applabs.newsappcompose.ui.components.NewsRowComponent
import com.applabs.newsappcompose.ui.viewmodel.NewsViewmodel
import com.applabs.utilities.ResourceState

const val TAG = "HOME-SCREEN"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewmodel: NewsViewmodel = hiltViewModel()
) {

    val newsRes by newsViewmodel.news.collectAsState()


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        20
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page: Int ->

        when (newsRes) {
            is ResourceState.Loading -> {
                Log.d(TAG, "HomeScreen: inside loading")
                Loader()

            }

            is ResourceState.Success -> {

                val response = (newsRes as ResourceState.Success).data
                Log.d(TAG, "HomeScreen: {${response.totalResults}}")
                if (response.articles.isNotEmpty()) {
                    NewsRowComponent(page, response.articles[page])
                } else {
                    EmptyStateComponent()
                }

            }

            is ResourceState.Error -> {

                val error = (newsRes as ResourceState.Error).error
                ErrorStateComponent(error)
                Log.d(TAG, "HomeScreen: $error")


            }

        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}