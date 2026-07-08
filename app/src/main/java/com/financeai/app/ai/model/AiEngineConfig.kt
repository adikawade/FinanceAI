package com.financeai.app.ai.model

/**
 * Master runtime configuration for the entire AI Engine.
 *
 * This configuration is downloaded from the backend
 * and controls every AI feature in FinanceAI.
 *
 * No AI behaviour should be hardcoded.
 */
data class AiEngineConfig(

    /**
     * Global AI switch.
     */
    val enabled: Boolean = true,

    /**
     * Maintenance mode.
     *
     * If enabled, AI requests should be rejected
     * gracefully.
     */
    val maintenanceMode: Boolean = false,

    /**
     * Enable AI analytics.
     */
    val analyticsEnabled: Boolean = true,

    /**
     * Enable AI request logging.
     */
    val loggingEnabled: Boolean = true,

    /**
     * Global cost protection.
     */
    val costProtectionEnabled: Boolean = true,

    /**
     * Emergency kill switch.
     *
     * Instantly disables all AI requests.
     */
    val emergencyKillSwitch: Boolean = false,

    /**
     * Default provider.
     */
    val defaultProvider: AiProvider,

    /**
     * Default model.
     */
    val defaultModel: AiModel,

    /**
     * Registered AI providers.
     */
    val providers: List<AiProviderConfig> = emptyList(),

    /**
     * Registered feature configurations.
     */
    val features: List<AiFeatureConfig> = emptyList(),

    /**
     * Registered usage policies.
     */
    val usagePolicies: List<AiUsagePolicy> = emptyList(),

    /**
     * Additional runtime metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)