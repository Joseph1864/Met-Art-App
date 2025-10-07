package com.example.metartapp.data

import com.example.metartapp.data.remote.dataRemoteModule
import com.example.metartapp.data.artwork.ArtworkRepository
import com.example.metartapp.data.remote.MetApi
import org.koin.dsl.module

val dataModule = module {

    includes(
        dataRemoteModule,
    )

    single<ArtworkRepository> {
        ArtworkRepository(
            api = get<MetApi>(),
        )
    }
}