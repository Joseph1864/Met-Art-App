package com.example.metartapp.screens.artwork

import com.example.metartapp.data.artwork.ArtworkRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val artworkModule = module {

    viewModel { (artworkId: Int) ->
        ArtworkScreenViewModel(
            artworkRepository = get<ArtworkRepository>(),
            artworkId = artworkId
        )
    }
}