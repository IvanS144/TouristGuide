package com.mr.touristguide.weather.domain.repository

import com.mr.touristguide.util.Resource
import com.mr.touristguide.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}