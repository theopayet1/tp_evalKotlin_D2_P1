/*
 * GpsManagerImpl.kt
 * Couche : Data · Manager
 * Rôle : suit la position avec le LocationManager d'Android.
 * TODO: à réécrire
 */
package com.example.dogceo.data.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Looper
import com.example.dogceo.domain.manager.GpsManager
import com.example.dogceo.domain.model.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val MIN_UPDATE_INTERVAL_MILLIS = 5_000L
private const val MIN_UPDATE_DISTANCE_METERS = 10f

/**
 * Suit la position de l'utilisateur et la publie dans [position].
 * Le GPS vide la batterie : il ne tourne qu'entre [start] et [stop].
 *
 * TODO: à réécrire
 *
 * @property context l'Application Context fourni par Koin, utilisé pour vérifier les permissions.
 */
class GpsManagerImpl(
    private val context: Context,
) : GpsManager, LocationListener {

    private val locationManager: LocationManager? = context.getSystemService(LocationManager::class.java)

    private val _position = MutableStateFlow<Position?>(null)

    override val position: StateFlow<Position?> = _position.asStateFlow()

    override fun start() {
        // Le Manager vérifie lui-même la permission : appelé trop tôt ou après un refus, il ne plante pas
        // TODO: à réécrire
        val provider = when {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED -> LocationManager.GPS_PROVIDER
            context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED -> LocationManager.NETWORK_PROVIDER
            else -> return
        }
        // Affiche tout de suite la dernière position connue, sans attendre le premier signal GPS
        // TODO: à réécrire
        locationManager?.getLastKnownLocation(provider)?.let(::onLocationChanged)
        locationManager?.requestLocationUpdates(
            provider,
            MIN_UPDATE_INTERVAL_MILLIS,
            MIN_UPDATE_DISTANCE_METERS,
            this,
            Looper.getMainLooper(),
        )
    }

    override fun stop() {
        locationManager?.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        // Mapping : l'objet Android Location ne sort pas de la couche Data
        // TODO: à réécrire
        _position.value = Position(latitude = location.latitude, longitude = location.longitude)
    }
}
