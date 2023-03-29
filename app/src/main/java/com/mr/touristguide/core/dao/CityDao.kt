package com.mr.touristguide.core.dao

import com.mr.touristguide.core.model.City

interface CityDao {
    fun getCities(): List<City>
    fun getById(id: Int): City
}