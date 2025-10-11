package com.example.metartapp

import android.app.Application
import com.example.metartapp.data.dataModule
import com.example.metartapp.screens.artwork.artworkModule
import com.example.metartapp.screens.home.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MetArtApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MetArtApp)
            modules(
                artworkModule,
                dataModule,
                homeModule,
            )
        }
    }
}