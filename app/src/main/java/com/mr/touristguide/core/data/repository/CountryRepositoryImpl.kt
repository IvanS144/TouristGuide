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

class CountryRepositoryImpl @Inject constructor(val app: Application) : CountryRepository {
    private val gson: Gson = Gson()

    override suspend fun getCountry(): Country {
        val locale = app.applicationContext.getString(R.string.locale)
//        var fileName = "state"
//        if (locale != "en") {
//            fileName = fileName + "_" + locale;
//        }
//        val packageName = app.packageName
//        val identifier = app.baseContext.resources.getIdentifier(fileName, "raw", packageName)
        val inputStream = app.applicationContext.resources.openRawResource(
            if (locale == "sr") {
                R.raw.state_sr
            } else {
                R.raw.state
            }
        )
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        withContext(Dispatchers.IO) {
            inputStream.close()
        }
        return gson.fromJson(json, Country::class.java)
    }

    override suspend fun getCountry(locale: String): Country {
        val inputStream = app.applicationContext.resources.openRawResource(
            if (locale == "sr") {
                R.raw.state_sr
            } else {
                R.raw.state
            }
        )
        val json = inputStream.readBytes().toString(Charset.defaultCharset())
        withContext(Dispatchers.IO) {
            inputStream.close()
        }
        return gson.fromJson(json, Country::class.java)
    }
}