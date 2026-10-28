package com.example.dogceo.data.manager

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.example.dogceo.data.R
import com.example.dogceo.domain.manager.SoundManager

/**
 * [SoundManager] avec [SoundPool] : le son est chargé une fois, puis rejoué tout de suite.
 *
 * @constructor lance le chargement du son du clic.
 * @param context sert juste à charger le fichier de `res/raw`, il n'est pas gardé.
 */
class SoundManagerImpl(context: Context) : SoundManager {

    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(1)
        .setAudioAttributes(
            AudioAttributes.Builder()
                // Son d'interface : Android le gère comme un bip, pas comme de la musique
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        .build()

    // load() est asynchrone, on attend la fin du chargement avant de jouer
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
