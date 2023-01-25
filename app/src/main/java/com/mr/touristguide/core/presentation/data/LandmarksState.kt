package com.mr.touristguide.core.presentation.data

import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Landmark

data class LandmarksState(
    val cities: List<Landmark>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
