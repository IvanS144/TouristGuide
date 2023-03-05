package com.mr.touristguide.core.data.remote

import com.mr.touristguide.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesApi {
    @Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashImage>

    @Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") term: String,
        @Query("per_page") perPage: Int
    ): SearchResult
}