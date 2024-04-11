package com.example.unsplash.presentation

sealed class ScreenRoute(val route: String) {
    data object SearchPhotoScreen: ScreenRoute("search_photos_screen")
    data object PhotoDetailScreen: ScreenRoute("photo_detail_screen")
}