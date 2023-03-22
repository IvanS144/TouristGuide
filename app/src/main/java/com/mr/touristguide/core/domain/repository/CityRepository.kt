package com.mr.touristguide.core.domain.repository

import com.mr.touristguide.core.model.City
import com.mr.touristguide.util.Resource

interface CityRepository {
    suspend fun getCities(): Resource<List<City>>
    suspend fun getCities(locale: String): Resource<List<City>>
}