package com.mr.touristguide.core.data.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mr.touristguide.R
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import com.mr.touristguide.core.data.remote.LandmarksApi
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import javax.inject.Inject

class LandmarkRepositoryImpl @Inject constructor(
    private val api: LandmarksApi,
    private val app: Application,
    private val preferencesRepository: PreferencesRepository
) : LandmarkRepository {
    private val gson: Gson = Gson()
    override suspend fun getLandmarks(): Resource<List<Landmark>> {
        val favorites = preferencesRepository.getFavoriteLandmarks()
//        return try{
//            val landmarks = mapToLandmarks(api.getLandmarks())
//            if(favorites.isNotEmpty()) {
//                landmarks.forEach { landmark ->
//                    if (favorites.contains(landmark.id.toString())) {
//                        landmark.isFavorite = true
//                    }
//                }
//            }
//            Resource.Success(landmarks)
//        }
//        catch(e: Exception){
//            println("Landmarks error")
        val locale = app.applicationContext.getString(R.string.locale)
//        var fileName = "landmarks"
//        if (locale != "en") {
//            fileName = fileName + "_" + locale;
//        }
//        val packageName = app.packageName
//        val identifier = app.baseContext.resources.getIdentifier(fileName, "raw", packageName)
        val inputStream = app.applicationContext.resources.openRawResource(
            if (locale == "en") {
                R.raw.landmarks
            } else {
                R.raw.landmarks_sr
            }
        )
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        withContext(Dispatchers.IO) {
            inputStream.close()
        }
        val typeToken = object : TypeToken<List<Landmark>>() {}.type
        val landmarks: List<Landmark> = gson.fromJson(json, typeToken)
        if (favorites.isNotEmpty()) {
            landmarks.forEach { landmark ->
                if (favorites.contains(landmark.id.toString())) {
                    landmark.isFavorite = true
                }
            }
        }
        return Resource.Error(message = "An error occurred", data = landmarks)
//        }

    }

    override suspend fun getLandmarks(locale: String): Resource<List<Landmark>> {
        val favorites = preferencesRepository.getFavoriteLandmarks()
        val inputStream = app.applicationContext.resources.openRawResource(
            if (locale == "en") {
                R.raw.landmarks
            } else {
                R.raw.landmarks_sr
            }
        )
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        withContext(Dispatchers.IO) {
            inputStream.close()
        }
        val typeToken = object : TypeToken<List<Landmark>>() {}.type
        val landmarks: List<Landmark> = gson.fromJson(json, typeToken)
        if (favorites.isNotEmpty()) {
            landmarks.forEach { landmark ->
                if (favorites.contains(landmark.id.toString())) {
                    landmark.isFavorite = true
                }
            }
        }
        return Resource.Error(message = "An error occurred", data = landmarks)
    }

    override suspend fun getFavoriteLandmarks(): List<Landmark>? {
        val favoriteLandmarkIdSet: Set<String> = preferencesRepository.getFavoriteLandmarks()
        val landmarks = getLandmarks().data
        return landmarks?.filter { landmark -> favoriteLandmarkIdSet.contains(landmark.id.toString()) }
            ?.toList()
    }

    override suspend fun addToFavoriteLandmarks(id: Int) {
        preferencesRepository.addToFavoriteLandmarks(id.toString())
    }

    override suspend fun removeFromFavoriteLandmarks(id: Int) {
        preferencesRepository.removeFromFavoriteLandmarks(id.toString())
    }

}