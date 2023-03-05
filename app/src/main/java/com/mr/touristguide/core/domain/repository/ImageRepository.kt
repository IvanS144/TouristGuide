package com.mr.touristguide.core.domain.repository

import androidx.paging.PagingData
import com.mr.touristguide.core.data.remote.UnsplashImage
import com.mr.touristguide.util.Resource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(): Flow<PagingData<UnsplashImage>>
    suspend fun searchImages(term: String, pageSize: Int, maxImages: Int): Flow<PagingData<UnsplashImage>>
}