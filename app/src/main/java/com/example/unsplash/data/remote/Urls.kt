package com.example.unsplash.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urls(
    val full: String?,
    val raw: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)