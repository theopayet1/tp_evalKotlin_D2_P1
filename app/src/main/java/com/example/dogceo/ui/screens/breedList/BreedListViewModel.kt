/*
 * BreedListViewModel.kt
 * Couche : UI · Écran liste des races
 * Rôle : reçoit les actions de l'écran et publie l'état à afficher.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.screens.breedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogceo.domain.manager.GpsManager
import com.example.dogceo.domain.manager.SoundManager
import com.example.dogceo.domain.repository.BreedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Cerveau de l'écran des races : reçoit les [BreedListAction] et publie un [BreedListUiState].
 *
 * TODO: à réécrire
 *
 * @property breedRepository contrat du Domain : le ViewModel ne sait pas que derrière il y a Room et Ktor.
 * @property soundManager contrat du Domain pour le son, libéré dans [onCleared].
 * @property gpsManager contrat du Domain pour la position, arrêté dès que l'écran n'est plus visible.
 */
class BreedListViewModel(
    private val breedRepository: BreedRepository,
    private val soundManager: SoundManager,
    private val gpsManager: GpsManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BreedListUiState())

    // Le GPS ne reprend au retour sur l'écran que si l'utilisateur a déjà accepté la localisation
    // TODO: à réécrire
    private var isLocationAllowed = false

    /** L'état lu par l'écran. En lecture seule : seul le ViewModel le modifie. TODO: à réécrire */
    val uiState: StateFlow<BreedListUiState> = _uiState.asStateFlow()

    init {
        observeBreeds()
        observePosition()
        // Premier téléchargement au démarrage, sans son : le son est réservé au clic
        // TODO: à réécrire
        refreshBreeds()
    }

    /**
     * Seule porte d'entrée de l'écran : le `when` oblige à traiter chaque action.
     *
     * TODO: à réécrire
     *
     * @param action ce que l'utilisateur vient de faire.
     */
    fun onAction(action: BreedListAction) {
        when (action) {
            BreedListAction.OnRefreshClick -> {
                soundManager.playClick()
                refreshBreeds()
            }
            BreedListAction.OnErrorDismiss -> _uiState.update { it.copy(hasError = false) }
            BreedListAction.OnTigerClick -> soundManager.playClick()
            is BreedListAction.OnLocationPermissionResult -> onLocationPermissionResult(action.granted)
            BreedListAction.OnLocationDeniedDismiss -> _uiState.update { it.copy(isLocationDenied = false) }
            BreedListAction.OnScreenResumed -> if (isLocationAllowed) gpsManager.start()
            BreedListAction.OnScreenPaused -> gpsManager.stop()
        }
    }

    private fun onLocationPermissionResult(granted: Boolean) {
        isLocationAllowed = granted
        _uiState.update { it.copy(isLocationDenied = !granted) }
        if (granted) gpsManager.start()
    }

    private fun observePosition() {
        gpsManager.position
            .onEach { position -> _uiState.update { it.copy(position = position) } }
            .launchIn(viewModelScope)
    }

    private fun observeBreeds() {
        breedRepository.observeBreeds()
            .onEach { breeds -> _uiState.update { it.copy(breeds = breeds) } }
            .launchIn(viewModelScope)
    }

    private fun refreshBreeds() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            val result = breedRepository.refreshBreeds()
            _uiState.update { it.copy(isLoading = false, hasError = result.isFailure) }
        }
    }

    override fun onCleared() {
        // Appelé une seule fois, quand l'écran est quitté pour de bon (pas lors d'une rotation) :
        // c'est le bon moment pour libérer la mémoire du SoundPool
        // TODO: à réécrire
        soundManager.release()
        gpsManager.stop()
    }
}
