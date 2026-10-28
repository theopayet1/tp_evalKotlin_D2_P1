package com.example.dogceo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dogceo.ui.core.theme.DogCeoTheme
import com.example.dogceo.ui.screens.breedList.BreedListScreen

/**
 * declaration de l'activity
 * applique le thème et affiche l'écran des races.
 *
 * @see BreedListScreen
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
