package com.example.unsplash.data.remote


import com.example.unsplash.domain.model.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotoResultDto(
    val results: List<Result>?,
    val total: Int?,
    @Json(name = "total_pages") val totalPages: Int?
)

fun SearchPhotoResultDto.toPhoto(): List<Photo> {
    return results!!.map {
        Photo(
            photoId = it.id!!,
            description = it.description,
            likes = it.likes,
            imageUrl = it.urls!!.raw!!,
            photographer = it.user!!.name
        )
    }
}