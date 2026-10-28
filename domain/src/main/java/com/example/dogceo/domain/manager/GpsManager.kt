/*
 * GpsManager.kt
 * Couche : Domain · Contrat
 * Rôle : décrit ce que l'app attend du GPS.
 * TODO: à réécrire
 */
package com.example.dogceo.domain.manager

import com.example.dogceo.domain.model.Position
import kotlinx.coroutines.flow.StateFlow

/**
 * Contrat de la localisation : l'UI suit la position sans savoir comment Android la calcule.
 * Nommé `GpsManager` et pas `LocationManager`, qui est déjà une classe Android.
 *
 * TODO: à réécrire
 */
interface GpsManager {

    /** Dernière position connue, `null` tant qu'aucune n'a été reçue. TODO: à réécrire */
    val position: StateFlow<Position?>

    /** Commence à suivre la position. Ne fait rien si la permission n'est pas accordée. TODO: à réécrire */
    fun start()

    /** Arrête de suivre la position, pour économiser la batterie. TODO: à réécrire */
    fun stop()
}
