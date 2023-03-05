package com.mr.touristguide.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="settings")

class PreferencesRepositoryImpl(private val context: Context) : PreferencesRepository {
    companion object{
        val MAX_IMAGES = intPreferencesKey(name= "MAX_IMAGES")
        val NEWS_CACHING = booleanPreferencesKey(name = "NEWS_CACHING")
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
}