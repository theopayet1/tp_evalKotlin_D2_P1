/*
 * SoundManagerImpl.kt
 * Couche : Data · Manager
 * Rôle : joue le son du bouton avec SoundPool.
 * TODO: à réécrire
 */
package com.example.dogceo.data.manager

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.example.dogceo.data.R
import com.example.dogceo.domain.manager.SoundManager

/**
 * Joue les sons courts de l'app avec [SoundPool] : le fichier est chargé une fois en mémoire,
 * puis rejoué instantanément à chaque clic.
 *
 * TODO: à réécrire
 *
 * @param context sert seulement à charger le fichier de `res/raw`, il n'est pas gardé.
 */
class SoundManagerImpl(context: Context) : SoundManager {

    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(1)
        .setAudioAttributes(
            AudioAttributes.Builder()
                // Un son d'interface, pas de la musique : Android l'ajuste comme tel
                // TODO: à réécrire
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        .build()

    // load() est asynchrone : on retient quand le fichier est prêt pour ne pas jouer un son vide
    // TODO: à réécrire
    private var isLoaded = false

    private val clickSoundId: Int = soundPool.load(context, R.raw.sound_refresh_click, 1)

    init {
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (sampleId == clickSoundId && status == 0) isLoaded = true
        }
    }

    override fun playClick() {
        if (!isLoaded) return
        soundPool.play(clickSoundId, 1f, 1f, 1, 0, 1f)
    }

    override fun release() {
        soundPool.release()
    }
}
