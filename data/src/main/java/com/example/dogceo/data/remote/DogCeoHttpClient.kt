/*
 * DogCeoHttpClient.kt
 * Couche : Data · Source distante
 * Rôle : crée le client Ktor configuré pour l'API.
 * TODO: à réécrire
 */
package com.example.dogceo.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val DOG_CEO_BASE_URL = "https://dog.ceo/api/"

/**
 * Fabrique le client [HttpClient] unique de l'app, déjà réglé pour l'API Dog CEO.
 *
 * TODO: à réécrire
 *
 * @return un client qui lit le JSON et préfixe chaque requête par l'adresse de l'API.
 */
fun createDogCeoHttpClient(): HttpClient = HttpClient(OkHttp) {
    // Une réponse 4xx / 5xx lève une exception au lieu d'être lue comme un DTO vide
    // TODO: à réécrire
    expectSuccess = true

    install(ContentNegotiation) {
        // L'app ne plante pas si l'API ajoute un champ qu'on ne connaît pas
        // TODO: à réécrire
        json(Json { ignoreUnknownKeys = true })
    }

    defaultRequest { url(DOG_CEO_BASE_URL) }
}
