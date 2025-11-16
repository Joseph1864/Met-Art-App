package com.example.metartapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.metartapp.screens.artwork.Image
import com.example.metartapp.screens.artwork.ImagePlaceholder
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onArtworkClick: (Int) -> Unit,
) {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val dailyArtwork = viewState.dailyArtwork

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Daily Artwork",
                style = MaterialTheme.typography.headlineSmall,
            )

            val imageUrl = dailyArtwork?.primaryImageUrl ?: dailyArtwork?.primaryImageSmallUrl

            if (!imageUrl.isNullOrEmpty()) {
                dailyArtwork?.let { artwork ->
                    Image(
                        imageUrl = imageUrl,
                        modifier = Modifier
                            .height(250.dp)
                            .aspectRatio(1f)
                            .align(Alignment.CenterHorizontally),
                        onClick = { onArtworkClick(artwork.id) }
                    )
                }
            } else {
                ImagePlaceholder(
                    modifier = Modifier
                        .height(250.dp)
                        .aspectRatio(1f)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}
