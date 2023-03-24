package com.mr.touristguide.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import com.mr.touristguide.R

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="settings")

class PreferencesRepositoryImpl(private val context: Context) : PreferencesRepository {
    companion object{
        val MAX_IMAGES = intPreferencesKey(name= "MAX_IMAGES")
        val NEWS_CACHING = booleanPreferencesKey(name = "NEWS_CACHING")
        val FAVORITE_LOCATIONS = stringSetPreferencesKey(name = "FAVORITE_LOCATIONS")
        val LOCALE = stringPreferencesKey(name = "LOCALE")
    }

    override suspend fun getMaxImages(): Int{
        val preferences = context.dataStore.data.first()
        return preferences[MAX_IMAGES]?: 10
    }

    override suspend fun setMaxImages(maxImages: Int) {
        context.dataStore.edit { settings -> settings[MAX_IMAGES] = maxImages }
    }

    override suspend fun getNewsCachingEnabled(): Boolean {
//        TODO("Not yet implemented")
        val preferences = context.dataStore.data.first()
        return preferences[NEWS_CACHING]?: true
    }

    override suspend fun setNewsCachingEnabled(newsCachingEnabled: Boolean) {
//        TODO("Not yet implemented")
        context.dataStore.edit { settings -> settings[NEWS_CACHING] = newsCachingEnabled }
    }

    override suspend fun addToFavoriteLandmarks(id: String){
        val preferences = context.dataStore.data.first()
        val oldSet = preferences[FAVORITE_LOCATIONS]?: setOf()
        if(!oldSet.contains(id)) {
            val newSet = mutableSetOf(id)
            newSet.addAll(oldSet)
            context.dataStore.edit { settings -> settings[FAVORITE_LOCATIONS] = newSet.toSet() }
        }
    }

    override suspend fun removeFromFavoriteLandmarks(id: String){
        val preferences = context.dataStore.data.first()
        val oldSet = preferences[FAVORITE_LOCATIONS]?: setOf()
        val newSet = oldSet.filter { landmarkId -> landmarkId!=id }.toSet()
        context.dataStore.edit { settings -> settings[FAVORITE_LOCATIONS] = newSet }
    }

    override suspend fun getFavoriteLandmarks(): Set<String>{
        val preferences = context.dataStore.data.first()
        return preferences[FAVORITE_LOCATIONS]?: setOf()
    }

    override suspend fun getLocale(): String {
        val preferences = context.dataStore.data.first()
        return preferences[LOCALE]?: context.getString(R.string.locale)
    }

    override suspend fun setLocale(locale: String) {
        context.dataStore.edit { settings -> settings[LOCALE] = locale}
    }
}