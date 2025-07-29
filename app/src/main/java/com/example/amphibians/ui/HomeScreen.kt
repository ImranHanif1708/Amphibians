package com.example.amphibians.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.data.Amphibians
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(amphibiansUiState: AmphibiansUiState, contentPadding: PaddingValues, onRetry: () -> Unit) {



    when (amphibiansUiState) {
        is AmphibiansUiState.Success -> AmphibiansList(amphibians = amphibiansUiState.amphibians, modifier = Modifier.padding(contentPadding))
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize().padding(contentPadding))
        is AmphibiansUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize().padding(contentPadding), onRetry)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier, onRetry: () -> Unit ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick =  onRetry ,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(R.string.retry))
          }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier.size(200.dp),
                painter = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.loading)
            )
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
}

@Composable
fun AmphibiansList(
    modifier: Modifier,
    amphibians: List<Amphibians>,
    contentPadding : PaddingValues = PaddingValues(0.dp)){
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(amphibians){
            AmphibianCard(
                amphibian = it,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibians, modifier: Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = amphibian.name + " (" + amphibian.type + ")",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.img_src)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "Amphibian Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = amphibian.description,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansAppPreview() {
    AmphibiansTheme {
        AmphibiansList(
            amphibians = listOf(
                Amphibians(
                    name = "Frog",
                    type = "Anura",
                    description = "A small amphibian.",
                    img_src = "https://example.com/frog.jpg"
                )
            ),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen(modifier = Modifier.fillMaxSize(),
            onRetry = { /* Retry action */ })

    }
}
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

