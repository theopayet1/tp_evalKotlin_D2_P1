/*
 * AppModule.kt
 * Couche : DI · Koin
 * Rôle : branche les implémentations de Data sur les contrats du Domain.
 * TODO: à réécrire
 */
package com.example.dogceo.di

import com.example.dogceo.data.local.DogCeoDatabase
import com.example.dogceo.data.local.createDogCeoDatabase
import com.example.dogceo.data.manager.SoundManagerImpl
import com.example.dogceo.data.remote.BreedApiService
import com.example.dogceo.data.remote.createDogCeoHttpClient
import com.example.dogceo.data.repository.BreedRepositoryImpl
import com.example.dogceo.domain.manager.SoundManager
import com.example.dogceo.domain.repository.BreedRepository
import com.example.dogceo.ui.screens.breedList.BreedListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Câblage Koin de toute l'app : c'est le seul endroit qui connaît à la fois le Domain et la Data.
 *
 * TODO: à réécrire
 */
val appModule = module {

    //region Base locale
    single { androidContext().createDogCeoDatabase() }
    single { get<DogCeoDatabase>().breedDao() }
    //endregion

    //region Réseau
    single { createDogCeoHttpClient() }
    singleOf(::BreedApiService)
    //endregion

    //region Repository
    // Le ViewModel demande l'interface BreedRepository, Koin lui donne BreedRepositoryImpl
    // TODO: à réécrire
    singleOf(::BreedRepositoryImpl) { bind<BreedRepository>() }
    //endregion

    //region Managers
    // factory : chaque ViewModel a son propre son, qu'il libère dans onCleared() sans couper celui des autres
    // TODO: à réécrire
    factory<SoundManager> { SoundManagerImpl(androidContext()) }
    //endregion

    //region ViewModels
    viewModelOf(::BreedListViewModel)
    //endregion
}
