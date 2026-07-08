package com.financeai.app.ai.network

import com.financeai.app.ai.model.AiProviderConfig

/**
 * Base HTTP client abstraction used by all AI providers.
 *
 * This interface isolates networking from provider logic.
 *
 * Future implementations:
 * - OkHttp
 * - Ktor
 * - Mock Client
 */
interface AiHttpClient {

    /**
     * Executes an HTTP POST request.
     */
    suspend fun post(

        config: AiProviderConfig,

        endpoint: String,

        headers: Map<String, String> = emptyMap(),

        body: String

    ): String

    /**
     * Executes an HTTP GET request.
     */
    suspend fun get(

        config: AiProviderConfig,

        endpoint: String,

        headers: Map<String, String> = emptyMap()

    ): String

    /**
     * Checks server availability.
     */
    suspend fun healthCheck(

        config: AiProviderConfig

    ): Boolean
}