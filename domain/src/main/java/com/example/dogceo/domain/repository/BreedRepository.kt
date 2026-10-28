package com.example.dogceo.domain.repository

import com.example.dogceo.domain.model.Breed
import kotlinx.coroutines.flow.Flow

/**
 * Contrat d'accès aux races : l'app lit toujours la copie locale, et la rafraîchit depuis l'API à la demande.
 *
 * TODO: à réécrire
 */
interface BreedRepository {

    /**
     * Suit la liste des races enregistrées sur le téléphone.
     *
     * TODO: à réécrire
     *
     * @return un [Flow] qui émet la liste à chaque changement, même sans réseau.
     */
    fun observeBreeds(): Flow<List<Breed>>

    /**
     * Télécharge les races depuis l'API et remplace la copie locale.
     *
     * TODO: à réécrire
     *
     * @return [Result.success] si la mise à jour a marché, [Result.failure] avec l'erreur sinon.
     */
    suspend fun refreshBreeds(): Result<Unit>
}
