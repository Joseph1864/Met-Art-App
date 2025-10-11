package com.example.metartapp.screens.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metartapp.data.artwork.Artwork
import com.example.metartapp.data.artwork.ArtworkRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtworkScreenViewModel(
    private val artworkRepository: ArtworkRepository,
    private val artworkId: Int,
): ViewModel() {

    private val _viewState = MutableStateFlow<ArtworkScreenViewState>(ArtworkScreenViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        fetchArtwork(artworkId = artworkId)
    }

    fun fetchArtwork(artworkId: Int)= viewModelScope.launch(
        CoroutineExceptionHandler { _, exception ->
            _viewState.update {
                ArtworkScreenViewState.Error
            }
        }
    ) {
        _viewState.update {
            ArtworkScreenViewState.Loading
        }
        val artwork = artworkRepository.getArtworkById(artworkId)

        _viewState.update {
            ArtworkScreenViewState.Content(artwork!!)
        }
    }



}

sealed interface ArtworkScreenViewState {

    data object Loading: ArtworkScreenViewState

    data class Content(
        val artwork: Artwork,
    ): ArtworkScreenViewState

    data object Error: ArtworkScreenViewState
}