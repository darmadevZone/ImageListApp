package com.example.unsplash.domain.use_case

import com.example.unsplash.common.NetworkResponse
import com.example.unsplash.data.remote.toPhoto
import com.example.unsplash.domain.model.Photo
import com.example.unsplash.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    // query -> 検索してもらう -> List<Photo>を返す関数
    suspend fun searchPhotos(query: String): Flow<NetworkResponse<List<Photo>>> = flow{
        emit(NetworkResponse.Loading())
        val photos = repository.searchPhotos(query).toPhoto();
        emit(NetworkResponse.Success(data = photos))
    }

    // invokeを使うと簡潔にかける
    operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val photos = repository.searchPhotos(query).toPhoto()
            emit(NetworkResponse.Success(data = photos))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure(error = e.toString()))
        }
    }
}