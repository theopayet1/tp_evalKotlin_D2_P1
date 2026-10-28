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
 * Gère l'écran des races : reçoit les [BreedListAction] et donne un [BreedListUiState].
 *
 * @property breedRepository donne les races (cache + API).
 * @property soundManager joue le son des boutons.
 * @property gpsManager donne la position, coupé quand l'écran est caché.
 */
class BreedListViewModel(
    private val breedRepository: BreedRepository,
    private val soundManager: SoundManager,
    private val gpsManager: GpsManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BreedListUiState())

    // Le GPS repart au retour sur l'écran seulement si la permission est accordée
    private var isLocationAllowed = false

    /** État lu par l'écran, seul le ViewModel le modifie. */
    val uiState: StateFlow<BreedListUiState> = _uiState.asStateFlow()

    init {
        observeBreeds()
        observePosition()
        // Premier chargement sans son, le son c'est seulement au clic
        refreshBreeds()
    }

    /**
     * Traite une action de l'écran. Pas de `else` dans le `when` : chaque nouvelle [BreedListAction] doit être gérée.
     *
     * @param action action de l'utilisateur.
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

    /**
     * Garde la réponse de la popup et lance le GPS si c'est accepté.
     *
     * @param granted `true` si une localisation est accordée.
     */
    private fun onLocationPermissionResult(granted: Boolean) {
        isLocationAllowed = granted
        _uiState.update { it.copy(isLocationDenied = !granted) }
        if (granted) gpsManager.start()
    }

    /** Met chaque nouvelle position dans l'état. */
    private fun observePosition() {
        gpsManager.position
            .onEach { position -> _uiState.update { it.copy(position = position) } }
            .launchIn(viewModelScope)
    }

    /** Met les races du cache dans l'état à chaque changement. */
    private fun observeBreeds() {
        breedRepository.observeBreeds()
            .onEach { breeds -> _uiState.update { it.copy(breeds = breeds) } }
            .launchIn(viewModelScope)
    }

    /** Retélécharge les races et met à jour [BreedListUiState.isLoading] et [BreedListUiState.hasError]. */
    private fun refreshBreeds() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            val result = breedRepository.refreshBreeds()
            _uiState.update { it.copy(isLoading = false, hasError = result.isFailure) }
        }
    }

    override fun onCleared() {
        // Appelé quand on quitte l'écran pour de bon (pas à la rotation), on libère le son et le GPS
        soundManager.release()
        gpsManager.stop()
    }
}
