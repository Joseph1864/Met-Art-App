package com.example.metartapp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metartapp.data.artwork.Artwork
import com.example.metartapp.data.artwork.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeScreenViewModel(
    private val artworkRepository: ArtworkRepository,
): ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        fetchDailyArtwork()
    }

    fun fetchDailyArtwork() = viewModelScope.launch {
        val artworkIdsWithImages = artworkRepository.getArtworkIdsWithImages()

        Log.d("HomeVM", artworkIdsWithImages?.size.toString())

        if (artworkIdsWithImages == null || artworkIdsWithImages.isEmpty()) {
            Log.d("HomeVM", "No artwork IDs found.")
            return@launch
        }

        val dailyArtworkId = createDailySeed(artworkIdsWithImages)
        val dailyArtwork = artworkRepository.getArtworkById(dailyArtworkId)

        dailyArtwork?.let{
            Log.d("HomeVM", "Fetched artwork: ${it.title}")
            _viewState.update { currentState ->
                currentState.copy(dailyArtwork = it)
            }
        } ?: Log.d("HomeVM", "Artwork is null")
    }

    private fun createDailySeed(
        artworkIdsWithImages: IntArray
    ): Int {
        val today = LocalDate.now()
        val seed = today.year * 10000 + today.monthValue * 100 + today.dayOfMonth

        val index = seed % artworkIdsWithImages.size
        val dailyArtworkId = artworkIdsWithImages[index]

        return dailyArtworkId
    }


}

data class HomeScreenViewState(
    val dailyArtwork: Artwork? = null,
)