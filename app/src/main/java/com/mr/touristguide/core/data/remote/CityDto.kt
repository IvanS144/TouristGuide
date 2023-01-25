package com.mr.touristguide.core.data.remote

import com.squareup.moshi.Json

data class CityDto(
    @field:Json(name="id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name="shortDescription")
    val shortDescription: String,
    @field:Json(name="mainDescription")
    val mainDescription: String,
    @field:Json(name="latitude")
    val latitude: Double,
    @field:Json(name="longitude")
    val longitude: Double
)