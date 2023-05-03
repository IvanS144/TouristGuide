package com.mr.touristguide.core.data.remote

import androidx.annotation.Keep

@Keep
data class SearchResult(
    val results: List<UnsplashImage>,
)