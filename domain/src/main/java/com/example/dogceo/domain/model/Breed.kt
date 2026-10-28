package com.example.dogceo.domain.model

/**
 * Une race de chien, telle que l'app la manipule.
 *
 * TODO: à réécrire
 *
 * @property name nom de la race, en minuscules comme dans l'API (`"australian"`).
 * @property subBreeds ses sous-races (`["kelpie", "shepherd"]`), vide si la race n'en a pas.
 */
data class Breed(
    val name: String,
    val subBreeds: List<String>,
)
