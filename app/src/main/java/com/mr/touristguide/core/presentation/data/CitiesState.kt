package com.mr.touristguide.core.presentation.data

import com.mr.touristguide.core.model.City

data class CitiesState(
    val cities: List<City>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
