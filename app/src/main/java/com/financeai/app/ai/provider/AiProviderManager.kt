package com.financeai.app.ai.provider

import com.financeai.app.ai.model.*

/**
 * Central manager responsible for selecting
 * and routing requests to the appropriate AI provider.
 *
 * This class NEVER hardcodes providers or models.
 * Everything is driven by Admin Panel configuration.
 */
class AiProviderManager(

    private val configurationRepository: AiConfigurationRepository,

    private val providers: List<AiProviderAdapter>

) {

    /**
     * Analyze a request using the configured provider.
     */
    suspend fun analyze(

        request: AiAnalysisRequest,

        context: AiRequestContext

    ): AiAnalysisResult {

        val engineConfig =
            configurationRepository.getEngineConfig()

        if (!engineConfig.enabled) {

            return disabledResult(
                "AI Engine is disabled."
            )
        }

        if (engineConfig.emergencyKillSwitch) {

            return disabledResult(
                "Emergency kill switch enabled."
            )
        }

        if (engineConfig.maintenanceMode) {

            return disabledResult(
                "AI Engine is under maintenance."
            )
        }

        val featureConfig =
            configurationRepository.getFeatureConfig(
                context.feature
            )
                ?: return disabledResult(
                    "Feature configuration not found."
                )

        if (!featureConfig.enabled) {

            return disabledResult(
                "Feature disabled."
            )
        }

        val usagePolicy =
            configurationRepository.getUsagePolicy(
                context.userPlan
            )
                ?: return disabledResult(
                    "Usage policy not found."
                )

        if (!usagePolicy.aiEnabled) {

            return disabledResult(
                "AI not available for this plan."
            )
        }

        val adapter = providers.firstOrNull {

            it.provider == featureConfig.provider

        } ?: return disabledResult(
            "Provider unavailable."
        )

        if (!adapter.isAvailable()) {

            return handleFallback(
                request,
                featureConfig
            )
        }

        return adapter.analyze(request)
    }

    /**
     * Fallback provider logic.
     */
    private suspend fun handleFallback(

        request: AiAnalysisRequest,

        config: AiFeatureConfig

    ): AiAnalysisResult {

        if (!config.enableFallback) {

            return disabledResult(
                "Fallback disabled."
            )
        }

        val fallback =
            providers.firstOrNull {

                it.provider ==
                        config.fallbackProvider

            } ?: return disabledResult(
                "Fallback provider unavailable."
            )

        return fallback.analyze(request)
    }

    /**
     * Standard disabled response.
     */
    private fun disabledResult(

        message: String

    ): AiAnalysisResult {

        return AiAnalysisResult(

            success = false,

            predictions = emptyList(),

            confidence = AiConfidence(

                score = 0f,

                level = ConfidenceLevel.VERY_LOW,

                requiresVerification = false,

                reason = message

            ),

            summary = message,

            explanation = message
        )
    }
}