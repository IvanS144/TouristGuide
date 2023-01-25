package com.mr.touristguide.news.data.remote

data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<HeadlineDto>
)
