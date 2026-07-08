package com.financeai.app.ai.cache

import java.time.Instant

/**
 * Metadata associated with a cached AI response.
 */
data class AiCacheMetadata(

    val provider: String,

    val model: String,

    val createdAt: Instant = Instant.now(),

    val lastAccessedAt: Instant = Instant.now(),

    val expiresAt: Instant,

    val hitCount: Int = 0,

    val tokenUsage: Int = 0,

    val estimatedCost: Double = 0.0,

    val responseTimeMs: Long = 0L,

    /**
     * Cache schema version.
     */
    val cacheVersion: Int = 1
)