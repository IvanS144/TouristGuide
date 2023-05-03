package com.mr.touristguide.news.data.remote

import androidx.annotation.Keep

@Keep
data class NewsDto(
    val status: String? = null,
    val totalResults: Int? = null,
    var articles: List<HeadlineDto> = listOf()
)
