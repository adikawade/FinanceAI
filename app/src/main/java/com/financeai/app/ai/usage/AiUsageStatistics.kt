package com.financeai.app.ai.usage

/**
 * AI usage analytics.
 *
 * Used by:
 * - User Dashboard
 * - Admin Dashboard
 * - AI Analytics
 */
data class AiUsageStatistics(

    /**
     * Total AI requests.
     */
    val totalRequests: Long = 0,

    /**
     * Successful requests.
     */
    val successfulRequests: Long = 0,

    /**
     * Failed requests.
     */
    val failedRequests: Long = 0,

    /**
     * Cache hits.
     */
    val cacheHits: Long = 0,

    /**
     * Cache misses.
     */
    val cacheMisses: Long = 0,

    /**
     * User AI credits consumed.
     */
    val creditsUsed: Long = 0,

    /**
     * Remaining user credits.
     */
    val creditsRemaining: Long = 0,

    /**
     * Provider tokens consumed.
     */
    val providerTokensUsed: Long = 0,

    /**
     * Estimated provider cost.
     */
    val estimatedCost: Double = 0.0,

    /**
     * Tokens saved due to cache.
     */
    val tokensSaved: Long = 0,

    /**
     * Estimated money saved by cache.
     */
    val estimatedCostSaved: Double = 0.0,

    /**
     * Average response time.
     */
    val averageResponseTimeMs: Long = 0,

    /**
     * Total AI chat messages.
     */
    val totalChatMessages: Long = 0,

    /**
     * Total SMS categorized.
     */
    val totalSmsProcessed: Long = 0
)