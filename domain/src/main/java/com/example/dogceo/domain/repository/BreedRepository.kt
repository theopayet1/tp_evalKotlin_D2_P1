package com.example.dogceo.domain.repository

import com.example.dogceo.domain.model.Breed
import kotlinx.coroutines.flow.Flow

/**
 * Accès aux races, en offline-first :
 * - [observeBreeds] lit toujours le cache local ;
 * - [refreshBreeds] met ce cache à jour depuis l'API.
 */
interface BreedRepository {

    /**
     * Suit les races en cache.
     *
     * @return un [Flow] qui émet la liste à chaque changement.
     */
    fun observeBreeds(): Flow<List<Breed>>

    /**
     * Télécharge les races et met à jour le cache.
     * Ne lève pas d'exception : l'erreur est dans le [Result].
     *
     * @return [Result.success] si le cache est à jour, [Result.failure] si l'appel a échoué.
     */
    suspend fun refreshBreeds(): Result<Unit>
}
