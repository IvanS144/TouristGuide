package com.mr.touristguide.core.data.repository

import android.app.Application
import com.google.gson.Gson
import com.mr.touristguide.R
import com.mr.touristguide.core.domain.repository.CountryRepository
import com.mr.touristguide.core.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(val app: Application) : CountryRepository{
    private val gson: Gson = Gson()

    override suspend fun getCountry(): Country{
        val inputStream = app.baseContext.resources.openRawResource(R.raw.state)
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        withContext(Dispatchers.IO) {
            inputStream.close()
        }
        return gson.fromJson(json, Country::class.java)
    }
}