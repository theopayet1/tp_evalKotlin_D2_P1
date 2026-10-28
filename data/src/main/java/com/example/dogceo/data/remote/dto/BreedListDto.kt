package com.example.dogceo.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Réponse JSON de `GET breeds/list/all`.
 *
 * @property message race → sous-races (liste vide s'il n'y en a pas).
 * @property status `"success"` si l'appel a marché.
 * @see com.example.dogceo.data.mapper.toEntities
 */
@Serializable
data class BreedListDto(
    val message: Map<String, List<String>>,
    val status: String,
)
