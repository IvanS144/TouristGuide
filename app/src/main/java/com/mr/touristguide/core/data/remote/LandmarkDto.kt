package com.mr.touristguide.core.data.remote

import androidx.annotation.Keep
import com.mr.touristguide.core.model.Section
import com.squareup.moshi.Json
@Keep
data class LandmarkDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "shortDescription")
    val shortDescription: String,
    @field:Json(name = "mainDescription")
    val mainDescription: String,
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double,
    val sections: List<Section>
)
