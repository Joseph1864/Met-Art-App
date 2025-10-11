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

class HomeScreenViewModel(
    private val artworkRepository: ArtworkRepository,
): ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun fetchArtwork() = viewModelScope.launch {
        val artwork = artworkRepository.getRandomArtwork()
        artwork?.let{
            _viewState.update { currentState ->
                currentState.copy(artwork = it)
            }
        }
    }


}

data class HomeScreenViewState(
    val artwork: Artwork? = null,
)