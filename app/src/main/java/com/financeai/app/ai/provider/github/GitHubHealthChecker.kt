package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.AiProviderConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

/**
 * Checks whether GitHub Models service
 * is reachable and healthy.
 *
 * Used by:
 * - Provider Manager
 * - Admin Panel
 * - Auto Fallback System
 */
class GitHubHealthChecker(

    private val client: OkHttpClient

) {

    /**
     * Returns true if provider is healthy.
     */
    suspend fun isHealthy(

        config: AiProviderConfig

    ): Boolean {

        return try {

            val request = Request.Builder()

                .url(config.baseUrl)

                .head()

                .build()

            client.newCall(request)

                .execute()

                .use {

                    it.isSuccessful

                }

        } catch (

            exception: Exception

        ) {

            false

        }
    }

    /**
     * Measures response time.
     */
    suspend fun ping(

        config: AiProviderConfig

    ): Long {

        return try {

            val start = System.currentTimeMillis()

            val request = Request.Builder()

                .url(config.baseUrl)

                .head()

                .build()

            client.newCall(request)

                .execute()

                .close()

            System.currentTimeMillis() - start

        } catch (

            exception: Exception

        ) {

            -1L

        }
    }

    /**
     * Returns provider status.
     */
    suspend fun status(

        config: AiProviderConfig

    ): GitHubHealthStatus {

        val latency = ping(config)

        return GitHubHealthStatus(

            available = latency >= 0,

            latencyMs = latency

        )
    }
}

/**
 * GitHub provider health information.
 */
data class GitHubHealthStatus(

    val available: Boolean,

    val latencyMs: Long
)