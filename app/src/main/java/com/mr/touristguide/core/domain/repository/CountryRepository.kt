package com.mr.touristguide.core.domain.repository

import com.mr.touristguide.core.model.Country

interface CountryRepository {
    suspend fun getCountry(): Country
    suspend fun getCountry(locale: String): Country
}