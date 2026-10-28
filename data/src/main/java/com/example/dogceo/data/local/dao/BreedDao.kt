package com.example.dogceo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dogceo.data.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

/**
 * Lecture et écriture de la table `breeds`.
 * Room génère le SQL à la compilation.
 *
 * @see BreedEntity
 */
@Dao
interface BreedDao {

    /**
     * Suit toutes les races,triées par nom.
     *
     * @return un [Flow] qui renvoie la liste à chaque changement de la table.
     */
    @Query("SELECT * FROM breeds ORDER BY name")
    fun observeAll(): Flow<List<BreedEntity>>

    /**
     * Ajoute les races, ou met à jour celles qui existent déjà
     *
     * @param entities races à enregistrer.
     */
    @Upsert
    suspend fun upsertAll(entities: List<BreedEntity>)
}
