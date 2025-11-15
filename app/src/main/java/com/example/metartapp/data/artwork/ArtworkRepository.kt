package com.example.metartapp.data.artwork

import com.example.metartapp.data.remote.MetApi

class ArtworkRepository(
    private val api: MetApi,
) {
    suspend fun getArtworkById(artworkId: Int): Artwork? {
        return try {
            val dto = api.getArtworkById(artworkId)
            dto.toDomainModel()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getArtworkIdsWithImages(): IntArray? {
        return try {
            api.getArtworkIdsWithImages().objectIds?.toIntArray()
        } catch (e: Exception) {
            null
        }
    }
}