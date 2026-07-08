package com.financeai.app.ai.cache

import com.financeai.app.ai.model.AiAnalysisResult
import java.time.Instant

/**
 * Represents a cached AI response.
 */
data class AiCacheEntry(

    /**
     * Unique cache key.
     */
    val key: String,

    /**
     * Cached AI response.
     */
    val result: AiAnalysisResult,

    /**
     * Cache creation time.
     */
    val createdAt: Instant = Instant.now(),

    /**
     * Expiration time.
     */
    val expiresAt: Instant,

    /**
     * Number of cache hits.
     */
    val hitCount: Int = 0
)