package com.example.dogceo.data.local

import androidx.room.TypeConverter

/** Séparateur des sous-races dans la colonne texte. Aucun nom de l'API ne contient de virgule. */
private const val SEPARATOR = ","

/**
 * Stocke la liste des sous-races en texte, car Room ne sait pas enregistrer une liste.
 *
 * @see com.example.dogceo.data.local.entity.BreedEntity.subBreeds
 */
class BreedConverters {

    /**
     * Convertit la liste en texte : `["kelpie", "shepherd"]` devient `"kelpie,shepherd"`.
     *
     * @param values sous-races à enregistrer.
     * @return le texte stocké en base.
     */
    @TypeConverter
    fun fromList(values: List<String>): String = values.joinToString(SEPARATOR)

    /**
     * Convertit le texte de la base en liste.
     *
     * @param value texte lu en base.
     * @return les sous-races, liste vide si le texte est vide.
     */
    @TypeConverter
    fun toList(value: String): List<String> =
        // split sur un texte vide donnerait [""] au lieu d'une liste vide
        if (value.isEmpty()) emptyList() else value.split(SEPARATOR)
}
