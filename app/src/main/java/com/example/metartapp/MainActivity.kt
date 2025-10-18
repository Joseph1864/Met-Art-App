package com.example.metartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.metartapp.screens.artwork.ArtworkScreen
import com.example.metartapp.screens.home.HomeScreen
import kotlinx.serialization.Serializable

class MainActivity: ComponentActivity() {
    @Serializable
    object Home
    @Serializable
    data class Artwork(val artworkId: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Home,
            ) {
                composable<Home> {
                    HomeScreen(
                        onArtworkClick = { artworkId ->
                            navController.navigate(Artwork(artworkId = artworkId))
                        }
                    )
                }

                composable<Artwork> { backStackEntry ->
                    val artwork = backStackEntry.toRoute<Artwork>()
                    ArtworkScreen(
                        artworkId = artwork.artworkId,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }

    }
}