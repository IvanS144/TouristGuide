package com.mr.touristguide.news.presentation

import com.mr.touristguide.news.data.remote.NewsDto

data class NewsState(
    val news: NewsDto? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
