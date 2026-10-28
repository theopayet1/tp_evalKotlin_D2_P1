package com.example.dogceo.ui.screens.breedList

/** Actions possibles sur l'écran des races, traitées par [BreedListViewModel.onAction].*/
sealed interface BreedListAction {

    /** Clic sur rafraîchir : joue le son et retélécharge les races. */
    data object OnRefreshClick : BreedListAction

    /** Fermeture du bandeau d'erreur.*/
    data object OnErrorDismiss : BreedListAction

    /** Clic sur l'icône tigre : joue le son.*/
    data object OnTigerClick : BreedListAction

    /**
     * Réponse à la popup de localisation.
     *
     * @property granted `true` si la position précise ou approximative est accordée.
     */
    data class OnLocationPermissionResult(val granted: Boolean) : BreedListAction

    /** Ferme le bandeau de refus de la localisation. */
    data object OnLocationDeniedDismiss : BreedListAction

    /** L'écran revient au premier plan, le GPS repart. */
    data object OnScreenResumed : BreedListAction

    /** L'écran passe en arrière-plan, le GPS s'arrête. */
    data object OnScreenPaused : BreedListAction
}
