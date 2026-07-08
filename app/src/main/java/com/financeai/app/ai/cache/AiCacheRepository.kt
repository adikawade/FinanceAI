package com.financeai.app.ai.cache

/**
 * Repository abstraction for AI cache.
 *
 * AI Engine should interact only with this interface.
 * Storage implementation can be changed without
 * modifying the engine.
 */
interface AiCacheRepository {

    /**
     * Returns cached entry.
     */
    suspend fun get(
        key: String
    ): AiCacheEntry?

    /**
     * Saves cache entry.
     */
    suspend fun save(
        entry: AiCacheEntry
    )

    /**
     * Removes cache entry.
     */
    suspend fun remove(
        key: String
    )

    /**
     * Clears entire cache.
     */
    suspend fun clear()

    /**
     * Clears cache for one feature.
     */
    suspend fun clearFeature(
        feature: String
    )

    /**
     * Returns cache statistics.
     */
    suspend fun statistics(): AiCacheStatistics

    /**
     * Returns current cache size.
     */
    suspend fun size(): Int
}