package com.example.dogceo.data.mapper

import com.example.dogceo.data.local.entity.BreedEntity
import com.example.dogceo.data.remote.dto.BreedListDto
import com.example.dogceo.domain.model.Breed

/**
 * Convertit la réponse de l'API en lignes pour Room.
 *
 * @receiver réponse de `breeds/list/all`.
 * @return une [BreedEntity] par clé de [BreedListDto.message].
 */
internal fun BreedListDto.toEntities(): List<BreedEntity> =
    message.map { (name, subBreeds) -> BreedEntity(name = name, subBreeds = subBreeds) }

/**
 * Convertit une ligne de Room en [Breed].
 *
 * @receiver race lue en base.
 * @return la race pour le Domain.
 */
internal fun BreedEntity.toDomain(): Breed =
    Breed(name = name, subBreeds = subBreeds)
