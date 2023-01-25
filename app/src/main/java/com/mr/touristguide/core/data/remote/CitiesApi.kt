package com.mr.touristguide.core.data.remote

import com.mr.touristguide.core.model.City
import retrofit2.http.GET

interface CitiesApi {
    @GET("/api/v1/cities")
    suspend fun getCities() : List<CityDto>
}