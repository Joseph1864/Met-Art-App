package com.example.metartapp.data.artwork

import com.example.metartapp.data.remote.ArtworkDto as RemoteArtwork

fun RemoteArtwork.toDomainModel(): Artwork = Artwork(
    id = objectID,
    title = title ?: "Untitled",
    artist = artistDisplayName ?: "Unknown",
    artistBio = artistDisplayBio,
    year = objectEndDate,
    primaryImageUrl = primaryImage,
    primaryImageSmallUrl = primaryImageSmall,
    medium = medium,
    dimensions = dimensions,
    department = department,
    classification = objectName,
    objectUrl = objectURL,
)