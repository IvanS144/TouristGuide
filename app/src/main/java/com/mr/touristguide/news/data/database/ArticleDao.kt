package com.mr.touristguide.news.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun findAll(): List<ArticleEntity>

    @Query("SELECT * FROM articles")
    fun getAllAsFlow(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM articles")
    suspend fun clearData()
}