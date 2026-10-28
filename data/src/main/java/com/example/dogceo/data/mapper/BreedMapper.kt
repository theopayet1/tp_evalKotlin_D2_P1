/*
 * BreedMapper.kt
 * Couche : Data · Mapper
 * Rôle : convertit DTO, Entity et modèle du Domain.
 * TODO: à réécrire
 */
package com.example.dogceo.data.mapper

import com.example.dogceo.data.local.entity.BreedEntity
import com.example.dogceo.data.remote.dto.BreedListDto
import com.example.dogceo.domain.model.Breed

/**
 * Transforme la réponse de l'API en lignes prêtes à enregistrer dans Room.
 *
 * TODO: à réécrire
 *
 * @receiver la réponse brute de `breeds/list/all`.
 * @return une [BreedEntity] par race de la map `message`.
 */
internal fun BreedListDto.toEntities(): List<BreedEntity> =
    message.map { (name, subBreeds) -> BreedEntity(name = name, subBreeds = subBreeds) }

/**
 * Transforme une ligne de Room en modèle du Domain, le seul que l'UI connaît.
 *
 * TODO: à réécrire
 *
 * @receiver une race lue dans la table `breeds`.
 * @return la [Breed] correspondante.
 */
internal fun BreedEntity.toDomain(): Breed =
    Breed(name = name, subBreeds = subBreeds)
