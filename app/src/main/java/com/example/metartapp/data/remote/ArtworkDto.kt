package com.example.metartapp.data.remote

data class ArtworkDto(
    val objectID: Int,
    val title: String?,
    val artistDisplayName: String?,
    val artistDisplayBio: String?,
    val objectEndDate: Int?,
    val primaryImage: String?,
    val primaryImageSmall: String?,
    val medium: String?,
    val dimensions: String?,
    val department: String?,
    val objectName: String?,
    val objectURL: String?,
)