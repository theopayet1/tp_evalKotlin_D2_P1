package com.example.dogceo.di

import com.example.dogceo.data.local.DogCeoDatabase
import com.example.dogceo.data.local.createDogCeoDatabase
import com.example.dogceo.data.manager.GpsManagerImpl
import com.example.dogceo.data.manager.SoundManagerImpl
import com.example.dogceo.data.remote.BreedApiService
import com.example.dogceo.data.remote.createDogCeoHttpClient
import com.example.dogceo.data.repository.BreedRepositoryImpl
import com.example.dogceo.domain.manager.GpsManager
import com.example.dogceo.domain.manager.SoundManager
import com.example.dogceo.domain.repository.BreedRepository
import com.example.dogceo.ui.screens.breedList.BreedListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Module Koin de l'app : relie chaque contrat à son implémentation dans Data.
 *
 * @see com.example.dogceo.DogCeoApplication
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
    singleOf(::BreedRepositoryImpl) { bind<BreedRepository>() }
    //endregion

    //region Managers
    factory<SoundManager> { SoundManagerImpl(androidContext()) }
    single<GpsManager> { GpsManagerImpl(androidContext()) }
    //endregion

    //region ViewModels
    viewModelOf(::BreedListViewModel)
    //endregion
}
