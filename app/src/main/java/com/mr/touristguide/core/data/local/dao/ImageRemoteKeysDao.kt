package com.mr.touristguide.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.touristguide.core.model.UnsplashRemoteKeys

@Dao
interface ImageRemoteKeysDao {
    @Query("SELECT * FROM image_remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM image_remote_keys")
    suspend fun clearRemoteKeys()
}