package com.mr.touristguide.core.dao.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mr.touristguide.R
import com.mr.touristguide.core.dao.CityDao
import com.mr.touristguide.core.model.City
import java.nio.charset.Charset

class CityDaoJsonImpl(val context: Context) : CityDao {
    private val gson: Gson = Gson()
    private val cities: List<City>

    init{
        val inputStream = context.resources.openRawResource(R.raw.cities)
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        inputStream.close()
        val typeToken = object: TypeToken<List<City>>(){}.type
        cities = gson.fromJson(json, typeToken)
    }
    override fun getCities(): List<City> {
        return cities;
    }

    override fun getById(id: Int): City {
        val city = cities.first { city -> city.id == id }
        return city;
    }
}