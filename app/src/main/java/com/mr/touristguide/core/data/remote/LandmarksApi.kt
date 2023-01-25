package com.mr.touristguide.core.data.remote

import retrofit2.http.GET

interface LandmarksApi {
    @GET("/api/v1/landmarks")
    suspend fun getLandmarks() : List<LandmarkDto>
}