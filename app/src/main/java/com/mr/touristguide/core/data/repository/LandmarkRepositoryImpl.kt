package com.mr.touristguide.core.data.repository

import com.mr.touristguide.core.data.mappers.mapToLandmarks
import com.mr.touristguide.core.data.remote.LandmarksApi
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.util.Resource
import javax.inject.Inject

class LandmarkRepositoryImpl @Inject constructor(private val api: LandmarksApi) : LandmarkRepository {
    override suspend fun getLandmarks(): Resource<List<Landmark>> {
        return try{
            Resource.Success(mapToLandmarks(api.getLandmarks()))
        }
        catch(e: Exception){
            e.printStackTrace()
            Resource.Error("An error occurred")
        }

    }

}