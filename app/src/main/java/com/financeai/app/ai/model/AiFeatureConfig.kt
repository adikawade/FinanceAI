package com.financeai.app.ai.model

/**
 * Configuration for an individual AI feature.
 *
 * Every AI feature (SMS, Tax, Investment, Budget, etc.)
 * has its own independent configuration.
 *
 * Loaded dynamically from the backend/Admin Panel.
 */
data class AiFeatureConfig(

    /**
     * AI feature.
     */
    val feature: AiFeature,

    /**
     * Whether this feature is enabled.
     */
    val enabled: Boolean = true,

    /**
     * Selected provider.
     */
    val provider: AiProvider,

    /**
     * Selected model.
     */
    val model: AiModel,

    /**
     * Minimum confidence required.
     */
    val minimumConfidence: Float = 0.80f,

    /**
     * Temperature override.
     *
     * Null = use provider default.
     */
    val temperature: Float? = null,

    /**
     * Max output tokens.
     *
     * Null = use provider default.
     */
    val maxOutputTokens: Int? = null,

    /**
     * Request timeout.
     *
     * Null = use provider default.
     */
    val timeoutSeconds: Int? = null,

    /**
     * Retry attempts.
     */
    val retryCount: Int = 2,

    /**
     * Enable automatic fallback.
     */
    val enableFallback: Boolean = true,

    /**
     * Fallback provider.
     */
    val fallbackProvider: AiProvider? = null,

    /**
     * Fallback model.
     */
    val fallbackModel: AiModel? = null,

    /**
     * Enable response caching.
     */
    val cacheEnabled: Boolean = true,

    /**
     * Cache duration.
     */
    val cacheDurationMinutes: Int = 60,

    /**
     * Whether user confirmation is required
     * below minimum confidence.
     */
    val requireUserConfirmation: Boolean = true,

    /**
     * Feature specific metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)