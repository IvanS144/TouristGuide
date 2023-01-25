package com.mr.touristguide

import com.mr.touristguide.core.data.remote.CitiesApi
import com.mr.touristguide.core.data.remote.LandmarksApi
import com.mr.touristguide.core.data.repository.CityRepositoryImpl
import com.mr.touristguide.core.data.repository.LandmarkRepositoryImpl
import com.mr.touristguide.core.domain.repository.CityRepository
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

//    @Provides
//    @Singleton
//    fun provideRetrofit() : Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("https://63cacd0ff36cbbdfc76091ca.mockapi.io")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//
//    }

    @Provides
    @Singleton
    fun provideCitiesApi(): CitiesApi{
//        return retrofit.create(CitiesApi::class.java)
        return Retrofit.Builder()
            .baseUrl("https://63cacd0ff36cbbdfc76091ca.mockapi.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create()

    }

    @Provides
    @Singleton
    fun provideLandmarksApi(): LandmarksApi {
//        return retrofit.create(CitiesApi::class.java)
        return Retrofit.Builder()
            .baseUrl("https://63cacd0ff36cbbdfc76091ca.mockapi.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create()

    }

//    @Provides
//    @Singleton
//    fun provideCitiesRepository(citiesApi: CitiesApi): CityRepository{
//        return CityRepositoryImpl(citiesApi)
//    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModules{
    @Binds
    @Singleton
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    abstract fun bindLandmarkRepository(landmarkRepositoryImpl: LandmarkRepositoryImpl): LandmarkRepository
}