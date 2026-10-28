package com.example.dogceo.domain.model

/**
 * Une position GPS.
 *
 * @property latitude en degrés, entre -90 et 90.
 * @property longitude en degrés, entre -180 et 180.
 */
data class Position(
    val latitude: Double,
    val longitude: Double,
)
