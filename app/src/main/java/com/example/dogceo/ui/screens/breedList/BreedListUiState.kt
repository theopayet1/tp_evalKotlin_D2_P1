package com.example.dogceo.ui.screens.breedList

import com.example.dogceo.domain.model.Breed
import com.example.dogceo.domain.model.Position

/**
 * Tout ce que l'écran des races affiche. Immuable, le ViewModel fait une copie à chaque changement.
 *
 * @property isLoading `true` pendant un téléchargement.
 * @property breeds races lues dans le cache.
 * @property hasError `true` si le dernier téléchargement a raté.
 * @property position position de l'utilisateur, `null` tant qu'il ne l'a pas demandée.
 * @property isLocationDenied `true` si l'utilisateur a refusé la localisation.
 */
data class BreedListUiState(
    val isLoading: Boolean = false,
    val breeds: List<Breed> = emptyList(),
    val hasError: Boolean = false,
    val position: Position? = null,
    val isLocationDenied: Boolean = false,
)
