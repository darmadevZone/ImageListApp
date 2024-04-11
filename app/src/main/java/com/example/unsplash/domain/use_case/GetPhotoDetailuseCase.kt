package com.example.unsplash.domain.use_case

import com.example.unsplash.common.NetworkResponse
import com.example.unsplash.data.remote.toPhotoDetail
import com.example.unsplash.domain.model.PhotoDetail
import com.example.unsplash.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoDetailuseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    // photoId -> 通信 -> 画像詳細を受け取る
    operator fun invoke(photoId: String): Flow<NetworkResponse<PhotoDetail>> = flow{
        try {
            emit(NetworkResponse.Loading())
            val photoDetail = repository.getPhotoById(photoId).toPhotoDetail()
            emit(NetworkResponse.Success(photoDetail))
        }catch (e:Exception){
            emit(NetworkResponse.Failure(error = e.toString()))
        }
    }
}