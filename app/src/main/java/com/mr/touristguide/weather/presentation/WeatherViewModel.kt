package com.mr.touristguide.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.touristguide.core.model.City
import com.mr.touristguide.weather.data.remote.WeatherApi
import com.mr.touristguide.weather.data.repository.WeatherRepositoryImpl
import com.mr.touristguide.weather.domain.repository.WeatherRepository
import com.mr.touristguide.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(city: City) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when(val result = repository.getWeatherData(city.latitude, city.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
}