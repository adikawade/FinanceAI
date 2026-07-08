package com.financeai.app.ai.model

/**
 * Runtime configuration for an AI provider.
 *
 * This configuration is loaded from the backend
 * (Admin Panel) and controls how the AI Engine
 * communicates with providers.
 *
 * No provider or model should be hardcoded.
 */
data class AiProviderConfig(

    /**
     * Provider.
     */
    val provider: AiProvider,

    /**
     * Selected model.
     */
    val model: AiModel,

    /**
     * Whether this provider is enabled.
     */
    val enabled: Boolean = true,

    /**
     * Base API URL.
     */
    val baseUrl: String = "",

    /**
     * API Key.
     *
     * Never hardcode.
     * Loaded securely from backend.
     */
    val apiKey: String = "",

    /**
     * Request timeout.
     */
    val timeoutSeconds: Int = 30,

    /**
     * Retry attempts.
     */
    val retryCount: Int = 2,

    /**
     * Temperature.
     */
    val temperature: Float = 0.2f,

    /**
     * Maximum output tokens.
     */
    val maxOutputTokens: Int = 2048,

    /**
     * Daily token limit.
     */
    val dailyTokenLimit: Long = 500_000,

    /**
     * Monthly token limit.
     */
    val monthlyTokenLimit: Long = 10_000_000,

    /**
     * Maximum allowed daily cost.
     */
    val dailyCostLimit: Double = 1000.0,

    /**
     * Maximum allowed monthly cost.
     */
    val monthlyCostLimit: Double = 30000.0,

    /**
     * Enable fallback.
     */
    val enableFallback: Boolean = true,

    /**
     * Backup provider.
     */
    val fallbackProvider: AiProvider? = null,

    /**
     * Backup model.
     */
    val fallbackModel: AiModel? = null
)