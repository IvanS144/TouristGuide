package com.mr.touristguide.core.data.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mr.touristguide.R
import com.mr.touristguide.core.data.mappers.mapToLandmarks
import com.mr.touristguide.core.data.remote.LandmarksApi
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import javax.inject.Inject

class LandmarkRepositoryImpl @Inject constructor(private val api: LandmarksApi, private val app: Application) : LandmarkRepository {
    private val gson: Gson = Gson()
    override suspend fun getLandmarks(): Resource<List<Landmark>> {
        return try{
            Resource.Success(mapToLandmarks(api.getLandmarks()))
        }
        catch(e: Exception){
            println("Landmarks error")
            val inputStream = app.baseContext.resources.openRawResource(R.raw.landmarks)
            val json = inputStream.readBytes().toString(Charset.defaultCharset())
            withContext(Dispatchers.IO) {
                inputStream.close()
            }
            val typeToken = object: TypeToken<List<Landmark>>(){}.type
            val landmarks: List<Landmark> = gson.fromJson(json, typeToken)
            Resource.Error(message = "An error occurred", data = landmarks)
        }

    }

}