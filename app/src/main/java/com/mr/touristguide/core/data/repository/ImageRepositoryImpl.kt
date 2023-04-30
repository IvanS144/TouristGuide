package com.mr.touristguide.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mr.touristguide.core.data.local.ImageDatabase
import com.mr.touristguide.core.data.paging.ImagesRemoteMediator
import com.mr.touristguide.core.data.paging.SearchPagingSource
import com.mr.touristguide.core.data.remote.ImagesApi
import com.mr.touristguide.core.data.remote.UnsplashImage
import com.mr.touristguide.core.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ImageRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi,
    private val imageDatabase: ImageDatabase
) : ImageRepository {
    override fun getImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { imageDatabase.imageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = ImagesRemoteMediator(
                imagesApi = imagesApi,
                imageDatabase = imageDatabase
            )
        ).flow
    }

    override suspend fun searchImages(
        term: String,
        pageSize: Int,
        maxImages: Int
    ): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {
                SearchPagingSource(
                    imagesApi = imagesApi,
                    query = term,
                    perPage = pageSize,
                    maxImages = maxImages
                )
            }
        ).flow
    }

    override suspend fun getImage(id: String): UnsplashImage{
        return imagesApi.getImage(id);
    }
}