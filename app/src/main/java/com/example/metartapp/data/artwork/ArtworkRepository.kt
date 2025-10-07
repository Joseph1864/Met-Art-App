package com.example.metartapp.data.artwork

import android.util.Log
import com.example.metartapp.data.remote.MetApi

class ArtworkRepository(
    private val api: MetApi,
) {
    suspend fun getRandomArtwork(): Artwork? {
        val randomId = (1..470000).random()
        return try {
            val dto = api.getArtworkById(randomId)
            if (!dto.primaryImage.isNullOrEmpty()) dto.toDomainModel() else null
        } catch (e: Exception) {
            null
        }
    }
}