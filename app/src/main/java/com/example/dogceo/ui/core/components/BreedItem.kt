/*
 * BreedItem.kt
 * Couche : UI · Composant partagé
 * Rôle : affiche une race et ses sous-races sur une ligne.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogceo.domain.model.Breed
import com.example.dogceo.ui.core.theme.DogCeoTheme

/**
 * Une ligne de la liste : le nom de la race, et ses sous-races en dessous s'il y en a.
 *
 * TODO: à réécrire
 *
 * @param breed la race à afficher.
 * @param modifier pour placer la ligne depuis l'écran parent.
 */
@Composable
fun BreedItem(
    breed: Breed,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(
            // L'API renvoie tout en minuscules : on met juste la première lettre en majuscule
            // TODO: à réécrire
            text = breed.name.replaceFirstChar(Char::uppercase),
            style = MaterialTheme.typography.titleMedium,
        )
        if (breed.subBreeds.isNotEmpty()) {
            Text(
                text = breed.subBreeds.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BreedItemPreview() {
    DogCeoTheme {
        BreedItem(breed = Breed(name = "australian", subBreeds = listOf("kelpie", "shepherd")))
    }
}
