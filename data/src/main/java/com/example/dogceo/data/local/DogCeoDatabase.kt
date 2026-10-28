package com.example.dogceo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dogceo.data.local.dao.BreedDao
import com.example.dogceo.data.local.entity.BreedEntity

/** Nom du fichier bd du téléphone*/
private const val DATABASE_NAME = "dogceo.db"

/**
 * Base locale de l'app garde les races.
 *
 * @see createDogCeoDatabase
 */
@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
@TypeConverters(BreedConverters::class)
abstract class DogCeoDatabase : RoomDatabase() {

    /**
     * Donne accès à la table des races.
     *
     * @return le [BreedDao], dont Room génère le code.
     */
    abstract fun breedDao(): BreedDao
}

/**
 * Crée la base Room de l'app.
 *
 * @receiver contexte d'où on prend l'`applicationContext`.
 * @return la base, à créer une seule fois (`single` dans Koin).
 */
fun Context.createDogCeoDatabase(): DogCeoDatabase =
    // applicationContext plutôt que celui d'un écran car la base ne garde pas une Activity fermée en mémoire logique
    Room.databaseBuilder(applicationContext, DogCeoDatabase::class.java, DATABASE_NAME).build()
