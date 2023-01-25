package com.mr.touristguide.news.data.remote

import java.util.UUID

data class HeadlineDto(
    var id: String?,
    val source: SourceDto,
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
