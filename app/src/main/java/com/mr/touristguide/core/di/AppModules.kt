package com.mr.touristguide

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mr.touristguide.core.data.local.ImageDatabase
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import com.mr.touristguide.core.data.preferences.PreferencesRepositoryImpl
import com.mr.touristguide.core.data.remote.CitiesApi
import com.mr.touristguide.core.data.remote.ImagesApi
import com.mr.touristguide.core.data.remote.LandmarksApi
import com.mr.touristguide.core.data.repository.CityRepositoryImpl
import com.mr.touristguide.core.data.repository.CountryRepositoryImpl
import com.mr.touristguide.core.data.repository.ImageRepositoryImpl
import com.mr.touristguide.core.data.repository.LandmarkRepositoryImpl
import com.mr.touristguide.core.domain.repository.CityRepository
import com.mr.touristguide.core.domain.repository.CountryRepository
import com.mr.touristguide.core.domain.repository.ImageRepository
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideCitiesApi(): CitiesApi {
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

    @Provides
    @Singleton
    fun provideImagesApi(): ImagesApi {
        val client = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create()
    }

    @Provides
    @Singleton
    fun provideImageDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(
            app,
            ImageDatabase::class.java,
            "images_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(@ApplicationContext context: Context): PreferencesRepository {
        return PreferencesRepositoryImpl(context)
    }

//    @Provides
//    @Singleton
//    fun provideCitiesRepository(citiesApi: CitiesApi): CityRepository{
//        return CityRepositoryImpl(citiesApi)
//    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModules {
    @Binds
    @Singleton
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    abstract fun bindLandmarkRepository(landmarkRepositoryImpl: LandmarkRepositoryImpl): LandmarkRepository

    @Binds
    @Singleton
    abstract fun bindImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    abstract fun bindCountryRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository
}