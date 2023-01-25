package com.mr.touristguide.core.data.mappers

import com.mr.touristguide.core.data.remote.CityDto
import com.mr.touristguide.core.data.remote.LandmarkDto
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Landmark

fun mapToCities(cityDtos: List<CityDto>): List<City>{
    return cityDtos.map{cityDto ->
        City(
            id = cityDto.id,
            name = cityDto.name,
            shortDescription = cityDto.shortDescription,
            mainDescription = cityDto.mainDescription,
            latitude = cityDto.latitude,
            longitude = cityDto.longitude)}.toList()
}

fun mapToLandmarks(landmarkDtos: List<LandmarkDto>) : List<Landmark>{
    return landmarkDtos.map{ landmarkDto ->
        Landmark(
            id = landmarkDto.id,
            name = landmarkDto.name,
            shortDescription = landmarkDto.shortDescription,
            mainDescription = landmarkDto.mainDescription,
            latitude = landmarkDto.latitude,
            longitude= landmarkDto.longitude)
    }
}