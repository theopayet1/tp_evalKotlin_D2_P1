package com.example.dogceo.data.repository

import com.example.dogceo.data.local.dao.BreedDao
import com.example.dogceo.data.mapper.toDomain
import com.example.dogceo.data.mapper.toEntities
import com.example.dogceo.data.remote.BreedApiService
import com.example.dogceo.domain.model.Breed
import com.example.dogceo.domain.repository.BreedRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implémentation de [BreedRepository] en mode offline-first : l'écran lit toujours Room,
 * et l'API ne sert qu'à mettre Room à jour.
 *
 * TODO: à réécrire
 *
 * @property breedApiService source distante, appelée seulement par [refreshBreeds].
 * @property breedDao source locale, la seule que lit [observeBreeds].
 */
class BreedRepositoryImpl(
    private val breedApiService: BreedApiService,
    private val breedDao: BreedDao,
) : BreedRepository {

    override fun observeBreeds(): Flow<List<Breed>> =
        breedDao.observeAll().map { entities -> entities.map { it.toDomain() } }

    override suspend fun refreshBreeds(): Result<Unit> =
        try {
            val response = breedApiService.getAllBreeds()
            breedDao.upsertAll(response.toEntities())
            Result.success(Unit)
        } catch (e: CancellationException) {
            // L'écran a été fermé : on laisse l'annulation remonter au lieu de la traiter comme une erreur réseau
            // TODO: à réécrire
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
}
