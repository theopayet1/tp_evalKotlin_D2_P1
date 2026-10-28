package com.example.dogceo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Une ligne de la table `breeds` : la copie locale d'une race, lisible sans réseau.
 *
 * TODO: à réécrire
 *
 * @property name nom de la race, clé primaire : un nouveau téléchargement remplace la ligne au lieu de la dupliquer.
 * @property subBreeds ses sous-races, rangées en texte grâce à [com.example.dogceo.data.local.BreedConverters].
 */
@Entity(tableName = "breeds")
data class BreedEntity(
    @PrimaryKey val name: String,
    val subBreeds: List<String>,
)
