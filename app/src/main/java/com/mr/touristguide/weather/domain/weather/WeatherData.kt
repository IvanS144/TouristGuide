package com.mr.touristguide.weather.domain.weather

import androidx.annotation.Keep
import java.time.LocalDateTime
@Keep
data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
