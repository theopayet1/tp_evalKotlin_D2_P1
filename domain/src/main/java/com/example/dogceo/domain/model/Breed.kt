package com.example.dogceo.domain.model

/**
 * Une race de chien.
 *
 * @property name nom en minuscules.
 * @property subBreeds sous-races, liste vide s'il n'y en a pas.
 */
data class Breed(
    val name: String,
    val subBreeds: List<String>,
)
