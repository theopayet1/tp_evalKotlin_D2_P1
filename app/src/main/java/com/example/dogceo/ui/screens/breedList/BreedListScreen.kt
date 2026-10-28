/*
 * BreedListScreen.kt
 * Couche : UI · Écran liste des races
 * Rôle : relie le ViewModel à l'affichage.
 * TODO: à réécrire
 */
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
 * Écran des races branché au ViewModel : il lit l'état et délègue tout l'affichage à [BreedListContent].
 *
 * TODO: à réécrire
 *
 * @param modifier pour placer l'écran depuis son parent.
 * @param viewModel fourni par Koin, remplaçable dans un test.
 */
@Composable
fun BreedListScreen(
    modifier: Modifier = Modifier,
    viewModel: BreedListViewModel = koinViewModel(),
) {
    // Arrête de lire l'état quand l'app passe en arrière-plan, et reprend au retour
    // TODO: à réécrire
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Ouvre la popup système des permissions ; la réponse remonte au ViewModel sous forme d'action
    // TODO: à réécrire
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { results ->
        viewModel.onAction(BreedListAction.OnLocationPermissionResult(granted = results.values.any { it }))
    }

    // Cycle de vie : le GPS ne tourne que quand l'écran est au premier plan (ON_RESUME)
    // et s'arrête dès qu'il passe en arrière-plan (ON_PAUSE) : un GPS allumé pour rien vide la batterie
    // TODO: à réécrire
    LifecycleResumeEffect(Unit) {
        viewModel.onAction(BreedListAction.OnScreenResumed)
        onPauseOrDispose { viewModel.onAction(BreedListAction.OnScreenPaused) }
    }

    BreedListContent(
        state = state,
        onAction = viewModel::onAction,
        onLocateClick = {
            // FINE et COARSE ensemble : obligatoire depuis Android 12
            // TODO: à réécrire
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
