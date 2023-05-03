package com.mr.touristguide.news.di

import android.app.Application
import androidx.room.Room
import com.mr.touristguide.news.data.database.ArticlesDatabase
import com.mr.touristguide.news.data.remote.NewsApi
import com.mr.touristguide.news.data.remote.NewsInterceptor
import com.mr.touristguide.news.data.repository.NewsRepositoryImpl
import com.mr.touristguide.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        val client = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .apply {
            addInterceptor(NewsInterceptor())
        }.build()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create()
    }

    @Provides
    @Singleton
    fun provideArticlesDatabase(app: Application): ArticlesDatabase {
        return Room.databaseBuilder(app, ArticlesDatabase::class.java, "articles_database")
            .fallbackToDestructiveMigration().build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}