package com.mr.touristguide.news.domain.repository

import com.mr.touristguide.news.data.remote.NewsDto
import com.mr.touristguide.util.Resource

interface NewsRepository {
    suspend fun getNews(): Resource<NewsDto>
}