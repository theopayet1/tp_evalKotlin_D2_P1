/*
 * BreedApiService.kt
 * Couche : Data · Source distante
 * Rôle : appelle l'API Dog CEO.
 * TODO: à réécrire
 */
package com.example.dogceo.data.remote

import com.example.dogceo.data.remote.dto.BreedListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Source distante des races : fait les appels HTTP vers l'API Dog CEO et renvoie des DTO.
 *
 * TODO: à réécrire
 *
 * @property httpClient le client créé par [createDogCeoHttpClient], fourni par Koin.
 */
class BreedApiService(
    private val httpClient: HttpClient,
) {

    /**
     * Récupère toutes les races et leurs sous-races.
     *
     * TODO: à réécrire
     *
     * @return la réponse brute de l'API, à convertir par le mapper.
     * @throws Exception si le réseau est coupé ou si l'API répond en erreur : c'est le repository qui la gère.
     */
    suspend fun getAllBreeds(): BreedListDto =
        httpClient.get("breeds/list/all").body()
}
