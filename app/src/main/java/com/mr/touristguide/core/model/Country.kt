package com.mr.touristguide.core.model

data class Country(
    val name: String,
    val shortDescription: String,
    val mainDescription: String,
    val mapCenterLatitude: Double,
    val mapCenterLongitude: Double,
    val searchTerm: String
    ) {
}