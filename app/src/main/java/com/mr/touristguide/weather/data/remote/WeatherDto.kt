package com.mr.touristguide.weather.data.remote

import com.mr.touristguide.weather.data.remote.WeatherDataDto
import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
