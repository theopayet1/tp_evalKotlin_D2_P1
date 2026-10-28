package com.example.dogceo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dogceo.data.local.dao.BreedDao
import com.example.dogceo.data.local.entity.BreedEntity

private const val DATABASE_NAME = "dogceo.db"

/**
 * Base locale unique de l'app : garde les races sur le téléphone pour un affichage hors ligne.
 *
 * TODO: à réécrire
 */
@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
@TypeConverters(BreedConverters::class)
abstract class DogCeoDatabase : RoomDatabase() {

    /** Donne accès à la table des races. TODO: à réécrire */
    abstract fun breedDao(): BreedDao
}

/**
 * Construit la base [DogCeoDatabase] à partir de n'importe quel [Context].
 *
 * TODO: à réécrire
 *
 * @receiver le Context fourni par Koin (`androidContext()`).
 * @return la base prête à l'emploi, à créer une seule fois (`single`).
 */
fun Context.createDogCeoDatabase(): DogCeoDatabase =
    // applicationContext vit aussi longtemps que l'app : la base ne retient pas un écran fermé en mémoire
    // TODO: à réécrire
    Room.databaseBuilder(applicationContext, DogCeoDatabase::class.java, DATABASE_NAME).build()
