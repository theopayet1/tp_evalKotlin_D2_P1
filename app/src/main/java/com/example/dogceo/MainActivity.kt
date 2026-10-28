/*
 * MainActivity.kt
 * Couche : App · Activity
 * Rôle : seule activity, affiche l'écran des races dans le thème.
 * TODO: à réécrire
 */
package com.example.dogceo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dogceo.ui.core.theme.DogCeoTheme
import com.example.dogceo.ui.screens.breedList.BreedListScreen

/**
 * Seule activity de l'app : elle pose le thème et affiche l'écran des races.
 *
 * TODO: à réécrire
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogCeoTheme {
                BreedListScreen()
            }
        }
    }
}
