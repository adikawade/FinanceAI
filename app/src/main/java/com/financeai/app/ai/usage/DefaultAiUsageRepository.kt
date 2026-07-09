package com.financeai.app.ai.usage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * Default implementation of AI Usage Repository.
 *
 * NOTE:
 * Current implementation uses in-memory storage.
 *
 * Future:
 * - Room
 * - Firestore
 * - Cloud Sync
 */
class DefaultAiUsageRepository : AiUsageRepository {

    private val usageRecords =
        MutableStateFlow<List<AiUsageRecord>>(emptyList())

    override suspend fun saveUsage(
        record: AiUsageRecord
    ) {

        usageRecords.value =
            usageRecords.value + record
    }

    override suspend fun getUsage(
        id: String
    ): AiUsageRecord? {

        return usageRecords.value.firstOrNull {

            it.id == id

        }
    }

    override fun getUserUsage(
        userId: String
    ): Flow<List<AiUsageRecord>> {

        return usageRecords.map {

            list ->

            list.filter {

                it.userId == userId

            }

        }
    }

    override suspend fun getTodayStatistics(
        userId: String
    ): AiUsageStatistics {

        val list = usageRecords.value.filter {

            it.userId == userId

        }

        return buildStatistics(list)
    }

    override suspend fun getMonthlyStatistics(
        userId: String
    ): AiUsageStatistics {

        val list = usageRecords.value.filter {

            it.userId == userId

        }

        return buildStatistics(list)
    }

    override suspend fun getCreditsUsed(
        userId: String
    ): Long {

        return usageRecords.value

            .filter {

                it.userId == userId

            }

            .sumOf {

                it.userTokens.toLong()

            }
    }

    override suspend fun getRemainingCredits(
        userId: String
    ): Long {

        // Managed by AiCreditManager
        return 0
    }

    override suspend fun clearUsage(
        userId: String
    ) {

        usageRecords.value =

            usageRecords.value.filterNot {

                it.userId == userId

            }
    }

    /**
     * Builds statistics.
     */
    private fun buildStatistics(

        list: List<AiUsageRecord>

    ): AiUsageStatistics {

        return AiUsageStatistics(

            totalRequests = list.size.toLong(),

            successfulRequests =

                list.count {

                    !it.cacheHit || it.provider != null

                }.toLong(),

            failedRequests = 0,

            cacheHits =

                list.count {

                    it.cacheHit

                }.toLong(),

            cacheMisses =

                list.count {

                    !it.cacheHit

                }.toLong(),

            creditsUsed =

                list.sumOf {

                    it.userTokens.toLong()

                },

            providerTokensUsed =

                list.sumOf {

                    it.providerTokens.toLong()

                },

            estimatedCost =

                list.sumOf {

                    it.providerCost

                }
        )
    }
}