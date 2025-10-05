package com.example.metartapp.data.artwork

import com.example.metartapp.data.remote.ArtworkDto as RemoteArtwork

fun RemoteArtwork.toDomainModel(): Artwork = Artwork(
    id = objectID,
    title = title ?: "Untitled",
    primaryImageUrl = primaryImage ?: ""
)