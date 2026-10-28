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

/** Temps minimum entre deux positions, en millisecondes. */
private const val MIN_UPDATE_INTERVAL_MILLIS = 5_000L

/** Distance minimum entre deux positions, en mètres. */
private const val MIN_UPDATE_DISTANCE_METERS = 10f

/**
 * [GpsManager] qui utilise le [LocationManager] d'Android.
 *
 * @constructor récupère le [LocationManager], `null` si le téléphone n'a pas de localisation.
 * @property context sert à vérifier les permissions.
 */
class GpsManagerImpl(
    private val context: Context,
) : GpsManager, LocationListener {

    private val locationManager: LocationManager? = context.getSystemService(LocationManager::class.java)

    private val _position = MutableStateFlow<Position?>(null)

    override val position: StateFlow<Position?> = _position.asStateFlow()

    /**
     * Lance le suivi avec la meilleure source autorisée :
     * - précise ([LocationManager.GPS_PROVIDER]) si `ACCESS_FINE_LOCATION` est accordée ;
     * - approximative ([LocationManager.NETWORK_PROVIDER]) sinon.
     *
     * Ne fait rien si aucune n'est accordée.
     */
    override fun start() {
        // Sans permission, start() ne fait rien au lieu de planter
        val provider = when {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED -> LocationManager.GPS_PROVIDER
            context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED -> LocationManager.NETWORK_PROVIDER
            else -> return
        }
        // Montre tout de suite la dernière position connue, sans attendre le GPS
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

    /**
     * Appelé par Android à chaque nouvelle position.
     *
     * @param location position reçue, transformée en [Position].
     */
    override fun onLocationChanged(location: Location) {
        // La Location d'Android reste dans Data, on la transforme en Position
        _position.value = Position(latitude = location.latitude, longitude = location.longitude)
    }
}
