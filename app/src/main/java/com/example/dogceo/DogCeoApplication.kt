/*
 * DogCeoApplication.kt
 * Couche : App · Démarrage
 * Rôle : démarre Koin avant le premier écran.
 * TODO: à réécrire
 */
package com.example.dogceo

import android.app.Application
import com.example.dogceo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Point d'entrée de l'app, créé avant tout écran : il démarre Koin une seule fois.
 *
 * TODO: à réécrire
 */
class DogCeoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Affiche dans Logcat ce que Koin crée : pratique pour repérer un oubli de branchement
            // TODO: à réécrire
            androidLogger()
            androidContext(this@DogCeoApplication)
            modules(appModule)
        }
    }
}
