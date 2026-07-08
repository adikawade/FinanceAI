package com.financeai.app.ai.provider

import com.financeai.app.ai.model.AiEngineConfig
import com.financeai.app.ai.model.AiFeature
import com.financeai.app.ai.model.AiFeatureConfig
import com.financeai.app.ai.model.AiProviderConfig
import com.financeai.app.ai.model.AiUsagePolicy

/**
 * Source of truth for AI configuration.
 *
 * This repository abstracts where the configuration comes from.
 *
 * Current:
 * - Local configuration
 *
 * Future:
 * - Firebase Remote Config
 * - Admin Panel API
 * - Offline Cache
 * - Cloud Sync
 */
interface AiConfigurationRepository {

    /**
     * Returns complete AI engine configuration.
     */
    suspend fun getEngineConfig(): AiEngineConfig

    /**
     * Returns configuration for a specific feature.
     */
    suspend fun getFeatureConfig(
        feature: AiFeature
    ): AiFeatureConfig?

    /**
     * Returns provider configuration.
     */
    suspend fun getProviderConfig(
        providerId: String
    ): AiProviderConfig?

    /**
     * Returns usage policy.
     *
     * Example:
     * FREE
     * PREMIUM
     * PRO
     */
    suspend fun getUsagePolicy(
        planId: String
    ): AiUsagePolicy?

    /**
     * Refresh configuration from backend.
     */
    suspend fun refresh()

    /**
     * Returns true if cached configuration exists.
     */
    suspend fun hasCachedConfiguration(): Boolean

    /**
     * Clears local configuration cache.
     */
    suspend fun clearCache()
}