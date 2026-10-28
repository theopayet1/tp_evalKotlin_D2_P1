package com.example.dogceo.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Réponse brute de `GET breeds/list/all`, telle que l'API Dog CEO l'envoie.
 *
 * TODO: à réécrire
 *
 * @property message chaque clé est une race, sa valeur la liste de ses sous-races (vide si aucune).
 * @property status `"success"` quand l'appel a réussi.
 */
@Serializable
data class BreedListDto(
    val message: Map<String, List<String>>,
    val status: String,
)
