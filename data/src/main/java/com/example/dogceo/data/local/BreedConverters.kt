/*
 * BreedConverters.kt
 * Couche : Data · Base locale
 * Rôle : range la liste des sous-races dans une colonne texte.
 * TODO: à réécrire
 */
package com.example.dogceo.data.local

import androidx.room.TypeConverter

private const val SEPARATOR = ","

/**
 * Apprend à Room à ranger une liste de textes, qu'il ne sait pas stocker seul, dans une seule colonne.
 *
 * TODO: à réécrire
 */
class BreedConverters {

    /** Transforme `["kelpie", "shepherd"]` en `"kelpie,shepherd"` avant l'écriture. TODO: à réécrire */
    @TypeConverter
    fun fromList(values: List<String>): String = values.joinToString(SEPARATOR)

    /** Fait l'inverse à la lecture. TODO: à réécrire */
    @TypeConverter
    fun toList(value: String): List<String> =
        // Sans ce test, une race sans sous-race donnerait [""] au lieu d'une liste vide
        // TODO: à réécrire
        if (value.isEmpty()) emptyList() else value.split(SEPARATOR)
}
