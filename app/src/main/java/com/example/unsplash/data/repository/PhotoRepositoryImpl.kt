package com.example.unsplash.data.repository

import com.example.unsplash.data.remote.PhotoDetailDto
import com.example.unsplash.data.remote.SearchPhotoResultDto
import com.example.unsplash.data.remote.UnsplashApiService
import com.example.unsplash.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService
) : PhotoRepository {
    override suspend fun searchPhotos(query: String): SearchPhotoResultDto {
        return apiService.searchPhotos(query)
    }

    override suspend fun getPhotoById(photoId: String): PhotoDetailDto {
        // cacheなどの処理を挟む
        return apiService.getPhotoById(photoId)
    }
}