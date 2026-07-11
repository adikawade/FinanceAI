package com.financeai.app.ai.usage

import com.financeai.app.ai.model.AiFeature
import com.financeai.app.ai.model.AiProvider
import java.time.Instant
import java.util.UUID

/**
 * Represents one AI usage entry.
 *
 * This model is stored by AiUsageRepository and
 * powers:
 * - User Usage
 * - Admin Analytics
 * - Credit System
 * - Cost Tracking
 * - Provider Statistics
 */
data class AiUsageRecord(

    /**
     * Unique usage id.
     */
    val id: String = UUID.randomUUID().toString(),

    /**
     * User id.
     */
    val userId: String,

    /**
     * AI Feature.
     */
    val feature: AiFeature,

    /**
     * AI Provider.
     */
    val provider: AiProvider? = null,

    /**
     * Provider model id.
     */
    val modelId: String? = null,

    /**
     * Cache hit.
     */
    val cacheHit: Boolean = false,

    /**
     * User credits consumed.
     */
    val userTokens: Int = 0,

    /**
     * Provider tokens consumed.
     */
    val providerTokens: Int = 0,

    /**
     * Estimated provider cost.
     */
    val providerCost: Double = 0.0,

    /**
     * Processing time.
     */
    val processingTimeMs: Long = 0L,

    /**
     * Successful execution.
     */
    val success: Boolean = true,

    /**
     * Error message if failed.
     */
    val errorMessage: String? = null,

    /**
     * Creation time.
     */
    val createdAt: Instant = Instant.now()
)