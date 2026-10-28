/*
 * BreedListAction.kt
 * Couche : UI · Écran liste des races
 * Rôle : liste les actions possibles de l'utilisateur.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.screens.breedList

/**
 * Liste fermée de tout ce que l'utilisateur peut faire sur l'écran des races.
 *
 * TODO: à réécrire
 */
sealed interface BreedListAction {

    /** Clic sur le bouton rafraîchir : joue le son puis retélécharge les races. TODO: à réécrire */
    data object OnRefreshClick : BreedListAction

    /** Clic sur « Fermer » dans le bandeau d'erreur. TODO: à réécrire */
    data object OnErrorDismiss : BreedListAction

    /** Clic sur l'icône tigre : l'écran affiche le tigre, le ViewModel joue le son. TODO: à réécrire */
    data object OnTigerClick : BreedListAction

    /**
     * Réponse de l'utilisateur à la popup de localisation.
     *
     * TODO: à réécrire
     *
     * @property granted `true` s'il a accepté la position précise ou approximative.
     */
    data class OnLocationPermissionResult(val granted: Boolean) : BreedListAction

    /** Clic sur « Fermer » dans le bandeau de refus de la localisation. TODO: à réécrire */
    data object OnLocationDeniedDismiss : BreedListAction

    /** L'écran revient au premier plan : le GPS peut reprendre. TODO: à réécrire */
    data object OnScreenResumed : BreedListAction

    /** L'écran passe en arrière-plan : le GPS s'arrête. TODO: à réécrire */
    data object OnScreenPaused : BreedListAction
}
