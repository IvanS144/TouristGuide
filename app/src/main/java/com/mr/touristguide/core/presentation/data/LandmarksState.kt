package com.mr.touristguide.core.presentation.data

import com.mr.touristguide.core.model.Landmark

data class LandmarksState(
    val landmarks: List<Landmark>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
