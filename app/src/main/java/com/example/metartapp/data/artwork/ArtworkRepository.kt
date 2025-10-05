package com.example.metartapp.data.artwork

import android.util.Log
import com.example.metartapp.data.remote.RetrofitInstance

class ArtworkRepository {
    suspend fun getRandomArtwork(): Artwork? {
        val randomId = (1..470000).random()
        return try {
            val dto = RetrofitInstance.api.getArtworkById(randomId)
            if (!dto.primaryImage.isNullOrEmpty()) dto.toDomainModel() else null
        } catch (e: Exception) {
            null
        }
    }
}