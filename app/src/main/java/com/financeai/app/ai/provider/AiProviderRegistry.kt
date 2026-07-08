package com.financeai.app.ai.provider

import com.financeai.app.ai.model.AiProvider

/**
 * Registry for all AI providers.
 *
 * Providers register themselves here.
 * The Provider Manager resolves providers
 * only through this registry.
 */
class AiProviderRegistry(

    providers: List<AiProviderAdapter>

) {

    private val providerMap: Map<AiProvider, AiProviderAdapter> =
        providers.associateBy { it.provider }

    /**
     * Returns provider by type.
     */
    fun getProvider(
        provider: AiProvider
    ): AiProviderAdapter? {

        return providerMap[provider]
    }

    /**
     * Returns all registered providers.
     */
    fun getAllProviders(): List<AiProviderAdapter> {

        return providerMap.values.toList()
    }

    /**
     * Returns true if provider exists.
     */
    fun hasProvider(
        provider: AiProvider
    ): Boolean {

        return providerMap.containsKey(provider)
    }

    /**
     * Returns available providers.
     */
    suspend fun getAvailableProviders(): List<AiProviderAdapter> {

        return providerMap.values.filter {

            it.isAvailable()

        }
    }
}