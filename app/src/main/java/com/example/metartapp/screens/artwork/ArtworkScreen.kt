package com.example.metartapp.screens.artwork

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metartapp.data.artwork.Artwork
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkScreen(
    artworkId: Int,
    onBackClick: () -> Unit,
) {

    val viewModel: ArtworkScreenViewModel = koinViewModel(parameters = {
        parametersOf(artworkId)
    })
    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (viewState) {
                is ArtworkScreenViewState.Content -> Content(
                    artwork = (viewState as ArtworkScreenViewState.Content).artwork
                )
                is ArtworkScreenViewState.Loading -> Loading(modifier = Modifier.fillMaxSize())
                is ArtworkScreenViewState.Error -> Error(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(
        space = 8.dp,
        alignment = Alignment.CenterVertically,
    ),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    CircularProgressIndicator()
}

@Composable
fun Content(
    artwork: Artwork,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 16.dp),
) {

    val imageUrl = artwork.primaryImageUrl ?: artwork.primaryImageSmallUrl

    if(!imageUrl.isNullOrEmpty()) {
        Image(
            imageUrl = imageUrl,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(400.dp),
        )
    } else {
        ImagePlaceholder(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(400.dp),
        )
    }


    Text(
        text = artwork.title,
        style = MaterialTheme.typography.titleLarge,
    )

    Text(
        text = artwork.artist,
        style = MaterialTheme.typography.titleSmall,
    )

    if (!artwork.artistBio.isNullOrEmpty()) {
        Text(
            text = artwork.artistBio,
            style = MaterialTheme.typography.titleSmall,
        )
    }


    Card(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = "Details",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Classification: ")
                    }
                    append(artwork.classification)
                },
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Medium: ")
                    }
                    append(artwork.medium)
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Dimensions: ")
                    }
                    append(artwork.dimensions)
                },
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Department: ")
                    }
                    append(artwork.department)
                },
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

    TextButton(

        onClick = {  },
        modifier = Modifier
            .align(Alignment.End),

        ) {
        Text(text = "Visit Website")
    }

}



@Composable
fun Error(modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(
        space = 8.dp,
        alignment = Alignment.CenterVertically,
    ),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Icon(
        modifier = Modifier.size(64.dp),
        imageVector = Icons.Default.Error,
        contentDescription = null,
    )
}

@Preview(showBackground = true)
@Composable
fun ArtworkContentPreview() {
    val sampleArtwork = Artwork(
        id = 441233,
        title = "The Annunciation",
        artist = "Peter Candid (Pieter de Witte, Pietro Candido)",
        artistBio = "Netherlandish, Bruges ca. 1548–1628 Munich",
        year = 1590,
        primaryImageUrl = "https://images.metmuseum.org/CRDImages/ep/original/DP-974-001.jpg",
        primaryImageSmallUrl = "https://images.metmuseum.org/CRDImages/ep/web-large/DP-974-001.jpg",
        medium = "Oil on wood",
        dimensions = "91 1/4 x 68 1/4 in. (231.8 x 173.3 cm)",
        department = "European Paintings",
        classification = "Painting",
        objectUrl = "https://www.metmuseum.org/art/collection/search/441233",
    )

    Content(artwork = sampleArtwork)
}
