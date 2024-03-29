package com.mr.touristguide.core.data.remote

import androidx.annotation.Keep
import androidx.room.Embedded
import com.squareup.moshi.Json
@Keep
data class User(
    @field:Json(name = "links")
    @Embedded
    val userLinks: UserLinks,
    val username: String,
) {
}