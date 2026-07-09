package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.AiProvider

/**
 * Handles provider fallback logic.
 *
 * Decision values are loaded dynamically
 * from Admin Panel configuration.
 */
class GitHubFallbackHandler {

    /**
     * Returns next provider when current
     * provider fails.
     */
    fun nextProvider(

        currentProvider: AiProvider,

        availableProviders: List<AiProvider>

    ): AiProvider? {

        if (availableProviders.isEmpty()) {

            return null
        }

        return availableProviders.firstOrNull {

            it != currentProvider

        }
    }

    /**
     * Returns true if fallback
     * should be attempted.
     */
    fun shouldFallback(

        statusCode: Int

    ): Boolean {

        return when (statusCode) {

            401 -> true

            403 -> true

            408 -> true

            429 -> true

            in 500..599 -> true

            else -> false
        }
    }

    /**
     * Returns retryable status.
     */
    fun shouldRetry(

        statusCode: Int

    ): Boolean {

        return when (statusCode) {

            408 -> true

            429 -> true

            in 500..599 -> true

            else -> false
        }
    }

    /**
     * Maximum retries.
     *
     * Runtime value will come
     * from Admin Panel.
     */
    fun maxRetries(): Int {

        return 3
    }
}