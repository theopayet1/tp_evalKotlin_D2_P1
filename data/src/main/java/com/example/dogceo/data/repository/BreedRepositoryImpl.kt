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
 * [BreedRepository] offline-first : l'écran lit Room, l'API sert juste à remplir Room.
 *
 * @property breedApiService appelé par [refreshBreeds].
 * @property breedDao lu par [observeBreeds], rempli par [refreshBreeds].
 */
class BreedRepositoryImpl(
    private val breedApiService: BreedApiService,
    private val breedDao: BreedDao,
) : BreedRepository {

    /** Lit Room et transforme chaque ligne en [Breed]. */
    override fun observeBreeds(): Flow<List<Breed>> =
        breedDao.observeAll().map { entities -> entities.map { it.toDomain() } }

    /**
     * Télécharge les races puis les enregistre dans Room.
     *
     * @return [Result.failure] si l'appel ou l'écriture a raté.
     * @throws CancellationException si la coroutine est annulée, on la laisse remonter.
     */
    override suspend fun refreshBreeds(): Result<Unit> =
        try {
            val response = breedApiService.getAllBreeds()
            breedDao.upsertAll(response.toEntities())
            Result.success(Unit)
        } catch (e: CancellationException) {
            // Écran fermé : ce n'est pas une erreur réseau, on laisse l'annulation remonter
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
}
