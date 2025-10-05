package com.example.metartapp.data.remote

import com.squareup.moshi.Json

data class ArtworkDto(
    @Json(name = "objectID") val objectID: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "primaryImage") val primaryImage: String?,
)