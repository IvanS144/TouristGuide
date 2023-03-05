package com.mr.touristguide.core.model

data class City(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val mainDescription: String,
    val latitude: Double,
    val longitude: Double,
    val searchTerm: String
    ) {
}