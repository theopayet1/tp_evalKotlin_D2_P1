package com.example.dogceo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Une race dans la table `breeds`, transformée en Breed par [com.example.dogceo.data.mapper.toDomain].
 *
 * @property name clé primaire, un nouveau téléchargement remplace la ligne au lieu d'en ajouter une.
 * @property subBreeds rangées en texte par [com.example.dogceo.data.local.BreedConverters].
 */
@Entity(tableName = "breeds")
data class BreedEntity(
    @PrimaryKey val name: String,
    val subBreeds: List<String>,
)
