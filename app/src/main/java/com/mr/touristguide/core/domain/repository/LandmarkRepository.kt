package com.mr.touristguide.core.domain.repository

import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.util.Resource

interface LandmarkRepository {
    suspend fun getLandmarks() : Resource<List<Landmark>>
}