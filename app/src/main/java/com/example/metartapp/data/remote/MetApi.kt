package com.example.metartapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MetApi {

    @GET("public/collection/v1/objects/{id}")
    suspend fun getArtworkById(
        @Path("id") id: Int,
    ): ArtworkDto
}