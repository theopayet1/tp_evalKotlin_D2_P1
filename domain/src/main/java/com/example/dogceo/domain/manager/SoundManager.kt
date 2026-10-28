package com.example.dogceo.domain.manager

/** Joue les sons de l'app.*/
interface SoundManager {

    /** Joue le son du clic. Ne fait rien si le son n'est pas encore chargé */
    fun playClick()

    /** Libère le son. À appeler quand l'écran qui l'utilise est détruit : ensuite, [playClick] ne joue plus rien */
    fun release()
}
