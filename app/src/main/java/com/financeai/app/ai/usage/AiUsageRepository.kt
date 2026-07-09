package com.financeai.app.ai.usage

import kotlinx.coroutines.flow.Flow

/**
 * Repository responsible for AI usage tracking.
 *
 * Every AI request should pass through this repository.
 */
interface AiUsageRepository {

    /**
     * Saves a usage record.
     */
    suspend fun saveUsage(
        record: AiUsageRecord
    )

    /**
     * Returns one usage record.
     */
    suspend fun getUsage(
        id: String
    ): AiUsageRecord?

    /**
     * Returns all usage records of user.
     */
    fun getUserUsage(
        userId: String
    ): Flow<List<AiUsageRecord>>

    /**
     * Returns today's statistics.
     */
    suspend fun getTodayStatistics(
        userId: String
    ): AiUsageStatistics

    /**
     * Returns monthly statistics.
     */
    suspend fun getMonthlyStatistics(
        userId: String
    ): AiUsageStatistics

    /**
     * Returns total credits consumed.
     */
    suspend fun getCreditsUsed(
        userId: String
    ): Long

    /**
     * Returns remaining credits.
     */
    suspend fun getRemainingCredits(
        userId: String
    ): Long

    /**
     * Clears all usage history.
     */
    suspend fun clearUsage(
        userId: String
    )
}