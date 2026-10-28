package com.example.dogceo.ui.screens.breedList

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * Écran des races branché au ViewModel, l'affichage est dans [BreedListContent].
 *
 * @param modifier modificateur de l'écran.
 * @param viewModel donné par Koin.
 */
@Composable
fun BreedListScreen(
    modifier: Modifier = Modifier,
    viewModel: BreedListViewModel = koinViewModel(),
) {
    // Arrête de lire l'état quand l'app est en arrière-plan
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Ouvre la popup de permission et envoie la réponse au ViewModel
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { results ->
        viewModel.onAction(BreedListAction.OnLocationPermissionResult(granted = results.values.any { it }))
    }

    // GPS allumé seulement quand l'écran est visible, sinon il vide la batterie
    LifecycleResumeEffect(Unit) {
        viewModel.onAction(BreedListAction.OnScreenResumed)
        onPauseOrDispose { viewModel.onAction(BreedListAction.OnScreenPaused) }
    }

    BreedListContent(
        state = state,
        onAction = viewModel::onAction,
        onLocateClick = {
            // Depuis Android 12, il faut demander FINE et COARSE ensemble
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
            )
        },
        modifier = modifier,
    )
}
