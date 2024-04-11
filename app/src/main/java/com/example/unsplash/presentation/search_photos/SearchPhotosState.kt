package com.example.unsplash.presentation.search_photos

import com.example.unsplash.domain.model.Photo

data class SearchPhotosState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null
)
