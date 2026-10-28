package com.example.dogceo.data.remote

import com.example.dogceo.data.remote.dto.BreedListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Appelle l'API Dog CEO.
 *
 * @property httpClient client créé par [createDogCeoHttpClient].
 */
class BreedApiService(
    private val httpClient: HttpClient,
) {

    /**
     * Récupère toutes les races et leurs sous-races.
     *
     * @return la réponse brute de l'API.
     * @throws io.ktor.client.plugins.ResponseException si l'API répond en 4xx ou 5xx.
     * @throws java.io.IOException si le réseau est coupé.
     */
    suspend fun getAllBreeds(): BreedListDto =
        httpClient.get("breeds/list/all").body()
}
