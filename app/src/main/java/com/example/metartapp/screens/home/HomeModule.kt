package com.example.metartapp.screens.home

import com.example.metartapp.data.artwork.ArtworkRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeScreenViewModel(get<ArtworkRepository>())
    }

}