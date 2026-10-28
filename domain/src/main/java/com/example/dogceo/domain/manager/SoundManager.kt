/*
 * SoundManager.kt
 * Couche : Domain · Contrat
 * Rôle : décrit ce que l'app attend du son.
 * TODO: à réécrire
 */
package com.example.dogceo.domain.manager

/**
 * Contrat du son de l'app : l'UI demande de jouer un son sans savoir comment Android le fait.
 *
 * TODO: à réécrire
 */
interface SoundManager {

    /** Joue le son du bouton rafraîchir. Ne fait rien si le son n'est pas encore chargé. TODO: à réécrire */
    fun playClick()

    /** Libère la mémoire du son. À appeler quand l'écran qui l'utilise disparaît. TODO: à réécrire */
    fun release()
}
