/*
 * Position.kt
 * Couche : Domain · Modèle
 * Rôle : une position GPS, sans aucun type Android.
 * TODO: à réécrire
 */
package com.example.dogceo.domain.model

/**
 * Position de l'utilisateur, telle que l'app la manipule.
 *
 * TODO: à réécrire
 *
 * @property latitude en degrés, de -90 (pôle Sud) à 90 (pôle Nord).
 * @property longitude en degrés, de -180 à 180.
 */
data class Position(
    val latitude: Double,
    val longitude: Double,
)
