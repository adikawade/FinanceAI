package com.financeai.app.ai.cache

import java.time.Instant

/**
 * Central cache manager.
 *
 * Responsible for cache validation,
 * expiration and cache policies.
 */
class AiCacheManager(

    private val cache: AiCache

) {

    suspend fun get(

        key: String

    ): AiCacheEntry? {

        val entry = cache.get(key)
            ?: return null

        if (Instant.now().isAfter(entry.expiresAt)) {

            cache.remove(key)

            return null
        }

        return entry.copy(

            hitCount = entry.hitCount + 1

        )
    }

    suspend fun save(

        entry: AiCacheEntry

    ) {

        cache.put(entry)
    }

    suspend fun clear() {

        cache.clear()
    }
}