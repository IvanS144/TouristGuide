package com.mr.touristguide.dao

import com.mr.touristguide.model.City

interface CityDao {
    fun getCities() : List<City>
    fun getById(id: Int) : City
}