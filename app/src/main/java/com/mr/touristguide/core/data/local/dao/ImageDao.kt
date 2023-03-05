package com.mr.touristguide.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.touristguide.core.data.remote.UnsplashImage

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<UnsplashImage>)

    @Query("DELETE FROM image")
    suspend fun clearImages()
}