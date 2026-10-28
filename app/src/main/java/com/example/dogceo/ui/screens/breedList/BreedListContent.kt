package com.example.dogceo.ui.screens.breedList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogceo.R
import com.example.dogceo.domain.model.Breed
import com.example.dogceo.domain.model.Position
import com.example.dogceo.ui.core.components.BreedItem
import com.example.dogceo.ui.core.components.ErrorBanner
import com.example.dogceo.ui.core.components.SpinningTiger
import com.example.dogceo.ui.core.theme.DogCeoTheme

/**
 * Affiche l'écran des races avec [state], sans ViewModel pour pouvoir faire des Previews.
 *
 * @param state état à afficher.
 * @param onAction envoie les actions de l'utilisateur.
 * @param onLocateClick clic sur « Me localiser », le parent demande la permission.
 * @param modifier modificateur de l'écran.
 * @see BreedListScreen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedListContent(
    state: BreedListUiState,
    onAction: (BreedListAction) -> Unit,
    onLocateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // État juste visuel, rememberSaveable le garde après une rotation
    var isTigerVisible by rememberSaveable { mutableStateOf(false) }
    val tigerDescription = stringResource(R.string.breed_list_tiger)

    Box(modifier = modifier) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.breed_list_title)) },
                    actions = {
                        IconButton(
                            onClick = {
                                isTigerVisible = true
                                onAction(BreedListAction.OnTigerClick)
                            },
                        ) {
                            Text(
                                // Easter egg : affiche le tigre
                                text = stringResource(R.string.tiger_emoji),
                                fontSize = 22.sp,
                                modifier = Modifier.semantics { contentDescription = tigerDescription },
                            )
                        }
                        IconButton(onClick = onLocateClick) {
                            Icon(
                                painter = painterResource(R.drawable.ic_location),
                                contentDescription = stringResource(R.string.breed_list_locate),
                            )
                        }
                        IconButton(
                            onClick = { onAction(BreedListAction.OnRefreshClick) },
                            // Bouton désactivé pendant un téléchargement, pour ne pas en lancer deux
                            enabled = !state.isLoading,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_refresh),
                                contentDescription = stringResource(R.string.breed_list_refresh),
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                if (state.isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                if (state.hasError) {
                    ErrorBanner(
                        message = stringResource(R.string.breed_list_error),
                        onDismiss = { onAction(BreedListAction.OnErrorDismiss) },
                    )
                }
                if (state.isLocationDenied) {
                    ErrorBanner(
                        message = stringResource(R.string.breed_list_location_denied),
                        onDismiss = { onAction(BreedListAction.OnLocationDeniedDismiss) },
                    )
                }
                state.position?.let { position ->
                    Text(
                        text = stringResource(R.string.breed_list_position, position.latitude, position.longitude),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                }
                if (state.breeds.isEmpty() && !state.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(text = stringResource(R.string.breed_list_empty))
                    }
                } else {
                    LazyColumn {
                        // key : Compose redessine seulement les races qui changent
                        items(state.breeds, key = { it.name }) { breed ->
                            BreedItem(breed = breed)
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        if (isTigerVisible) {
            SpinningTiger(onClose = { isTigerVisible = false })
        }
    }
}

private val previewBreeds = listOf(
    Breed(name = "akita", subBreeds = emptyList()),
    Breed(name = "australian", subBreeds = listOf("kelpie", "shepherd")),
    Breed(name = "bulldog", subBreeds = listOf("boston", "english", "french")),
)

@Preview(showBackground = true, name = "Liste")
@Composable
private fun BreedListContentPreview() {
    DogCeoTheme {
        BreedListContent(state = BreedListUiState(breeds = previewBreeds), onAction = {}, onLocateClick = {})
    }
}

@Preview(showBackground = true, name = "Chargement")
@Composable
private fun BreedListContentLoadingPreview() {
    DogCeoTheme {
        BreedListContent(state = BreedListUiState(isLoading = true), onAction = {}, onLocateClick = {})
    }
}

@Preview(showBackground = true, name = "Erreur")
@Composable
private fun BreedListContentErrorPreview() {
    DogCeoTheme {
        BreedListContent(state = BreedListUiState(breeds = previewBreeds, hasError = true), onAction = {}, onLocateClick = {})
    }
}

@Preview(showBackground = true, name = "Position et refus")
@Composable
private fun BreedListContentLocationPreview() {
    DogCeoTheme {
        BreedListContent(
            state = BreedListUiState(
                breeds = previewBreeds,
                position = Position(latitude = 47.3220, longitude = 5.0415),
                isLocationDenied = true,
            ),
            onAction = {},
            onLocateClick = {},
        )
    }
}
