package com.example.metartapp.data.artwork

data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val artistBio: String?,
    val year: Int?,
    val primaryImageUrl: String?,
    val primaryImageSmallUrl: String?,
    val medium: String?,
    val dimensions: String?,
    val department: String?,
    val classification: String?,
    val objectUrl: String?,
)