/*
 * BreedListUiState.kt
 * Couche : UI · Écran liste des races
 * Rôle : décrit tout ce que l'écran affiche.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.screens.breedList

import com.example.dogceo.domain.model.Breed
import com.example.dogceo.domain.model.Position

/**
 * Tout ce que l'écran des races affiche, à un instant donné. Immuable : le ViewModel en crée une copie à chaque changement.
 *
 * TODO: à réécrire
 *
 * @property isLoading `true` pendant un téléchargement : l'écran montre la barre de progression.
 * @property breeds les races à afficher, lues dans la base locale.
 * @property hasError `true` si le dernier téléchargement a échoué : l'écran affiche le bandeau d'erreur.
 * @property position la position de l'utilisateur, `null` tant qu'il n'a pas demandé à être localisé.
 * @property isLocationDenied `true` si l'utilisateur a refusé la localisation : l'écran explique ce qu'il perd.
 */
data class BreedListUiState(
    val isLoading: Boolean = false,
    val breeds: List<Breed> = emptyList(),
    val hasError: Boolean = false,
    val position: Position? = null,
    val isLocationDenied: Boolean = false,
)
