package com.mr.touristguide.core.data.remote

import retrofit2.http.GET

interface CitiesApi {
    @GET("/api/v1/landmarks")
    suspend fun getCities(): List<CityDto>
}