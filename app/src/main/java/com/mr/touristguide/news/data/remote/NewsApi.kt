package com.mr.touristguide.news.data.remote

import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/top-headlines?country=rs&category=sports")
    suspend fun getNews(): NewsDto
}