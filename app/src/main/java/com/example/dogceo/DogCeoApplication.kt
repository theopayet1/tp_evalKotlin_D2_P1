package com.example.dogceo

import android.app.Application
import com.example.dogceo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Point d'entrée de l'app, créé avant tout écran :
 * il démarre simplement Koin une seule fois.
 *
 * @see appModule
 */
class DogCeoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DogCeoApplication)
            modules(appModule)
        }
    }
}
