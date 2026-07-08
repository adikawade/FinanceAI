package com.financeai.app.ai.cache

/**
 * Default cache repository implementation.
 *
 * Current:
 * - In-memory placeholder
 *
 * Future:
 * - Room
 * - Redis
 * - Disk LRU
 * - Cloud Cache
 */
class DefaultAiCacheRepository(

    private val cache: AiCache

) : AiCacheRepository {

    override suspend fun get(
        key: String
    ): AiCacheEntry? {

        return cache.get(key)
    }

    override suspend fun save(
        entry: AiCacheEntry
    ) {

        cache.put(entry)
    }

    override suspend fun remove(
        key: String
    ) {

        cache.remove(key)
    }

    override suspend fun clear() {

        cache.clear()
    }

    override suspend fun clearFeature(
        feature: String
    ) {

        // Future implementation:
        // Remove all entries belonging
        // to this feature.
    }

    override suspend fun statistics(): AiCacheStatistics {

        return AiCacheStatistics(

            totalEntries = 0,

            cacheHits = 0,

            cacheMisses = 0,

            estimatedTokensSaved = 0,

            estimatedCostSaved = 0.0,

            hitRatio = 0f
        )
    }

    override suspend fun size(): Int {

        return 0
    }
}