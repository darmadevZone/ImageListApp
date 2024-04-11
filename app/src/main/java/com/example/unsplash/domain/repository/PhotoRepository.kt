package com.example.unsplash.domain.repository

import com.example.unsplash.data.remote.PhotoDetailDto
import com.example.unsplash.data.remote.SearchPhotoResultDto

interface PhotoRepository {
    suspend fun searchPhotos(query: String): SearchPhotoResultDto

    suspend fun getPhotoById(photoId: String): PhotoDetailDto
}