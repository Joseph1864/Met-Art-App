package com.example.metartapp.data.artwork

import com.example.metartapp.data.remote.MetApi

class ArtworkRepository(
    private val api: MetApi,
) {
    private val cache = mutableMapOf<Int, Artwork>()

    suspend fun getArtworkById(artworkId: Int): Artwork? {
        return try {
            cache[artworkId]?.let { return it }

            val dto = api.getArtworkById(artworkId)
            cache[artworkId] = dto.toDomainModel()

            dto.toDomainModel()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getArtworkIdsWithImages(): IntArray? {
        return try {
            api.getArtworkIdsWithImages().objectIDs?.toIntArray()
        } catch (e: Exception) {
            null
        }
    }

    fun saveArtworkToCache(artwork: Artwork) {
        cache[artwork.id] = artwork
    }
}