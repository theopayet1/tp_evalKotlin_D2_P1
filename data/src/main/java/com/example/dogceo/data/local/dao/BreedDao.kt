/*
 * BreedDao.kt
 * Couche : Data · Base locale
 * Rôle : lit et écrit la table des races.
 * TODO: à réécrire
 */
package com.example.dogceo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dogceo.data.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

/**
 * Accès à la table `breeds` : Room génère le code SQL à la compilation (KSP).
 *
 * TODO: à réécrire
 */
@Dao
interface BreedDao {

    /**
     * Suit toutes les races, triées par nom.
     *
     * TODO: à réécrire
     *
     * @return un [Flow] qui réémet la liste dès que la table change.
     */
    @Query("SELECT * FROM breeds ORDER BY name")
    fun observeAll(): Flow<List<BreedEntity>>

    /**
     * Insère les races, ou met à jour celles qui existent déjà.
     *
     * TODO: à réécrire
     *
     * @param entities les races téléchargées depuis l'API.
     */
    @Upsert
    suspend fun upsertAll(entities: List<BreedEntity>)
}
