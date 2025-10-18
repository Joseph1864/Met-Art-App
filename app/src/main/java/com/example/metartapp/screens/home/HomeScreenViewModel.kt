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
import kotlin.ranges.random

class HomeScreenViewModel(
    private val artworkRepository: ArtworkRepository,
): ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun fetchArtwork() = viewModelScope.launch {
        val artwork = artworkRepository.getArtworkById((1..470000).random())
        artwork?.let{
            Log.d("HomeVM", "Fetched artwork: ${it.title}")
            _viewState.update { currentState ->
                currentState.copy(artwork = it)
            }
        } ?: Log.d("HomeVM", "Artwork is null")
    }


}

data class HomeScreenViewState(
    val artwork: Artwork? = null,
)