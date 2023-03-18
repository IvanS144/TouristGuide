package com.mr.touristguide.core.model

data class Landmark(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val mainDescription: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false
)
