package com.mr.touristguide.dao.impl

import android.content.Context
import android.content.res.Resources
import android.content.res.loader.ResourcesLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mr.touristguide.R
import com.mr.touristguide.dao.CityDao
import com.mr.touristguide.model.City
import java.nio.charset.Charset

class CityDaoJsonImpl(val context: Context) : CityDao {
    private val gson: Gson = Gson()
    override fun getCities(): List<City> {
        val inputStream = context.resources.openRawResource(R.raw.cities)
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        inputStream.close()
        val typeToken = object: TypeToken<List<City>>(){}.type
        return gson.fromJson(json, typeToken)

    }
}