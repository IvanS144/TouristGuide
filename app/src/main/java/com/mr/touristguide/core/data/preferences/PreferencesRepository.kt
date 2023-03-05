package com.mr.touristguide.core.data.preferences

interface PreferencesRepository {
    suspend fun getMaxImages(): Int
    suspend fun setMaxImages(maxImages: Int)
    suspend fun getNewsCachingEnabled(): Boolean
    suspend fun setNewsCachingEnabled(newsCachingEnabled: Boolean)
}