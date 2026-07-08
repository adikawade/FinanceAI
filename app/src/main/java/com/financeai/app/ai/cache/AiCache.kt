package com.financeai.app.ai.cache

/**
 * Cache contract used by the AI Engine.
 */
interface AiCache {

    /**
     * Returns cached response.
     */
    suspend fun get(
        key: String
    ): AiCacheEntry?

    /**
     * Stores AI response.
     */
    suspend fun put(
        entry: AiCacheEntry
    )

    /**
     * Removes cache entry.
     */
    suspend fun remove(
        key: String
    )

    /**
     * Clears all cached entries.
     */
    suspend fun clear()

    /**
     * Returns true if cache exists.
     */
    suspend fun contains(
        key: String
    ): Boolean
}