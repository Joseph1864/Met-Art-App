package com.example.metartapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetApi {

    @GET("public/collection/v1/objects/{id}")
    suspend fun getArtworkById(
        @Path("id") id: Int,
    ): ArtworkDto

    @GET("public/collection/v1/search")
    suspend fun getArtworkIdsWithImages(
        @Query("q") q: String = "painting",
        @Query("hasImages") hasImages: Boolean = true
    ): ArtworkSearchResultDto
}