package com.mr.touristguide.core.data.preferences

interface PreferencesRepository {
    suspend fun getMaxImages(): Int
    suspend fun setMaxImages(maxImages: Int)
    suspend fun getNewsCachingEnabled(): Boolean
    suspend fun setNewsCachingEnabled(newsCachingEnabled: Boolean)
    suspend fun addToFavoriteLandmarks(id: String)
    suspend fun removeFromFavoriteLandmarks(id: String)
    suspend fun getFavoriteLandmarks(): Set<String>
    suspend fun getLocale(): String
    suspend fun setLocale(locale: String)
}