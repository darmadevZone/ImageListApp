package com.example.unsplash.di

import com.example.unsplash.common.Constants.BASE_URL
import com.example.unsplash.data.remote.UnsplashApiService
import com.example.unsplash.data.repository.PhotoRepositoryImpl
import com.example.unsplash.domain.repository.PhotoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUnsplashApiService(): UnsplashApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).build().create(UnsplashApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(api: UnsplashApiService): PhotoRepository {
        return PhotoRepositoryImpl(api)
    }

}