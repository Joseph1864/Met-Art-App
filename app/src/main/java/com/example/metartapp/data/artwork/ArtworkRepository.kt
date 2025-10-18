package com.example.metartapp.data.artwork

import android.util.Log
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
}