package com.financeai.app.ai.cache

/**
 * Cache analytics.
 */
data class AiCacheStatistics(

    val totalEntries: Int,

    val cacheHits: Long,

    val cacheMisses: Long,

    val estimatedTokensSaved: Long,

    val estimatedCostSaved: Double,

    val hitRatio: Float
)