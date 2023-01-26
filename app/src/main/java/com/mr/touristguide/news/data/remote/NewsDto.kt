package com.mr.touristguide.news.data.remote

data class NewsDto(
    val status: String? = null,
    val totalResults: Int? = null,
    var articles: List<HeadlineDto> = listOf()
)
