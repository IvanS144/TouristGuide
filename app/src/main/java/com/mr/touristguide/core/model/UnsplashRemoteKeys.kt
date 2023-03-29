package com.mr.touristguide.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_remote_keys")
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
