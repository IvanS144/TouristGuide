package com.mr.touristguide.core.data.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mr.touristguide.R
import com.mr.touristguide.core.data.mappers.mapToCities
import com.mr.touristguide.core.data.remote.CitiesApi
import com.mr.touristguide.core.domain.repository.CityRepository
import com.mr.touristguide.core.model.City
import com.mr.touristguide.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val api: CitiesApi, private val app: Application) : CityRepository{
    private val gson: Gson = Gson()
    override suspend fun getCities(): Resource<List<City>> {
        return try{
            println("request sent")
            Resource.Success(
                data = mapToCities(api.getCities())
            )
        }
        catch(e: Exception){
            println("ERROR")
            val inputStream = app.baseContext.resources.openRawResource(R.raw.cities)
            val json = inputStream.readBytes().toString(Charset.defaultCharset())
            withContext(Dispatchers.IO) {
                inputStream.close()
            }
            val typeToken = object: TypeToken<List<City>>(){}.type
            val cities: List<City> = gson.fromJson(json, typeToken)
            Resource.Error(message ="An error occurred", data = cities)
        }
    }
}