package com.mr.touristguide.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.touristguide.core.data.local.dao.ImageDao
import com.mr.touristguide.core.data.local.dao.ImageRemoteKeysDao
import com.mr.touristguide.core.data.remote.UnsplashImage
import com.mr.touristguide.core.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun remoteKeysDao(): ImageRemoteKeysDao
}