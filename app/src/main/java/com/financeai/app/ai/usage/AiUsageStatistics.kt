package com.financeai.app.ai.usage

import com.financeai.app.ai.model.AiProvider

/**
 * Aggregated AI usage statistics.
 *
 * Used by:
 * - User Dashboard
 * - Admin Dashboard
 * - Analytics
 * - Billing
 * - Cost Monitoring
 */
data class AiUsageStatistics(

    /**
     * User id.
     */
    val userId: String,

    /**
     * Total AI requests.
     */
    val totalRequests: Long,

    /**
     * Successful requests.
     */
    val successfulRequests: Long,

    /**
     * Failed requests.
     */
    val failedRequests: Long,

    /**
     * Cached responses.
     */
    val cacheHits: Long,

    /**
     * Non cached responses.
     */
    val cacheMisses: Long,

    /**
     * Total user credits consumed.
     */
    val totalUserTokens: Long,

    /**
     * Total provider tokens consumed.
     */
    val totalProviderTokens: Long,

    /**
     * Total provider cost.
     */
    val totalProviderCost: Double,

    /**
     * Average execution time.
     */
    val averageProcessingTimeMs: Double,

    /**
     * Most frequently used provider.
     */
    val favouriteProvider: AiProvider? = null
) {

    /**
     * Cache hit percentage.
     */
    val cacheHitRate: Double
        get() {

            val total = cacheHits + cacheMisses

            return if (total == 0L) {

                0.0

            } else {

                cacheHits.toDouble() / total.toDouble()
            }
        }

    /**
     * Success percentage.
     */
    val successRate: Double
        get() {

            if (totalRequests == 0L) {

                return 0.0
            }

            return successfulRequests.toDouble() /
                    totalRequests.toDouble()
        }

    /**
     * Average provider cost per request.
     */
    val averageRequestCost: Double
        get() {

            if (totalRequests == 0L) {

                return 0.0
            }

            return totalProviderCost / totalRequests
        }

    /**
     * Average provider tokens per request.
     */
    val averageProviderTokens: Double
        get() {

            if (totalRequests == 0L) {

                return 0.0
            }

            return totalProviderTokens.toDouble() /
                    totalRequests.toDouble()
        }

    /**
     * Average user credits per request.
     */
    val averageUserTokens: Double
        get() {

            if (totalRequests == 0L) {

                return 0.0
            }

            return totalUserTokens.toDouble() /
                    totalRequests.toDouble()
        }
}