package com.mr.touristguide.core.data.repository

import com.mr.touristguide.core.data.mappers.mapToCities
import com.mr.touristguide.core.data.remote.CitiesApi
import com.mr.touristguide.core.domain.repository.CityRepository
import com.mr.touristguide.core.model.City
import com.mr.touristguide.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

class CityRepositoryImpl @Inject constructor(private val api: CitiesApi) : CityRepository{
    override suspend fun getCities(): Resource<List<City>> {
        return try{
            println("request sent")
            Resource.Success(
                data = mapToCities(api.getCities())
            )
        }
        catch(e: Exception){
            println("ERROR")
            e.printStackTrace()
            Resource.Error("An error occurred")
        }
    }
}