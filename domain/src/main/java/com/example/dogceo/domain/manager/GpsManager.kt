package com.example.dogceo.domain.manager

import com.example.dogceo.domain.model.Position
import kotlinx.coroutines.flow.StateFlow

/**
 * Suit la position de l'utilisateur.
 *
 * Appelé comme ça pour ne pas le confondre avec le `LocationManager` d'Android.
 */
interface GpsManager {

    /** Dernière position reçue, `null` tant qu'il n'y en a pas. */
    val position: StateFlow<Position?>

    /**
     * Lance le suivi. Ne fait rien sans la permission de localisation.
     *
     * @see stop
     */
    fun start()

    /**
     * Arrête le suivi pour économiser la batterie.
     *
     * @see start
     */
    fun stop()
}
