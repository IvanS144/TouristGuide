package com.mr.touristguide.weather.domain.weather

import androidx.annotation.Keep

@Keep
data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
