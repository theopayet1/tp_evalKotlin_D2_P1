package com.example.dogceo.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/** Adresse de l'API, ajoutée devant chaque requête.*/
private const val DOG_CEO_BASE_URL = "https://dog.ceo/api/"

/**
 * Crée le client HTTP de l'app, réglé pour l'API Dog CEO.
 *
 * @return un client qui lit le JSON et préfixe chaque URL par [DOG_CEO_BASE_URL].
 * @see BreedApiService
 */
fun createDogCeoHttpClient(): HttpClient = HttpClient(OkHttp) {
    // Une réponse 4xx ou 5xx lève une exception au lieu de donner un DTO vide
    expectSuccess = true

    install(ContentNegotiation) {
        // Un champ inconnu dans le JSON est ignoré au lieu de faire planter
        json(Json { ignoreUnknownKeys = true })
    }

    defaultRequest { url(DOG_CEO_BASE_URL) }
}
