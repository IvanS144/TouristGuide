package com.mr.touristguide.weather.data.repository

import com.mr.touristguide.weather.data.mappers.toWeatherInfo
import com.mr.touristguide.weather.data.remote.WeatherApi
import com.mr.touristguide.weather.domain.repository.WeatherRepository
import com.mr.touristguide.util.Resource
import com.mr.touristguide.weather.domain.weather.WeatherInfo
import javax.inject.Inject

//import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}