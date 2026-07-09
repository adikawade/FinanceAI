package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.cache.AiCacheManager
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.provider.AiProviderManager
import com.financeai.app.ai.usage.AiUsageTracker

/**
 * Executes a single AI request.
 *
 * Responsibilities:
 * - Cache lookup
 * - Provider execution
 * - Usage tracking
 * - Cache update
 *
 * Business rules and permissions are handled
 * by the orchestrator before reaching here.
 */
class AiRequestExecutor(

    private val providerManager: AiProviderManager,

    private val cacheManager: AiCacheManager,

    private val usageTracker: AiUsageTracker

) {

    /**
     * Executes one AI request.
     */
    suspend fun execute(

        context: AiExecutionContext

    ): AiExecutionResult {

        // -------------------------
        // Cache Lookup
        // -------------------------

        if (context.cacheEnabled) {

            val cached = cacheManager.get(

                context.request

            )

            if (cached != null) {

                return AiExecutionResult(

                    executionId = context.executionId,

                    success = true,

                    result = cached,

                    cacheHit = true,

                    executionTime = java.time.Duration.ZERO,

                    providerTokens = 0,

                    userCredits = 0,

                    estimatedCost = 0.0
                )
            }
        }

        // -------------------------
        // Provider Execution
        // -------------------------

        val started = System.nanoTime()

        val providerResult: AiAnalysisResult =

            providerManager.execute(

                provider = context.provider,

                model = context.model,

                request = context.request
            )

        val duration =

            java.time.Duration.ofNanos(

                System.nanoTime() - started
            )

        // -------------------------
        // Usage Tracking
        // -------------------------

        if (context.usageTrackingEnabled) {

            usageTracker.record(

                context = context,

                result = providerResult
            )
        }

        // -------------------------
        // Cache Update
        // -------------------------

        if (context.saveToCache) {

            cacheManager.put(

                request = context.request,

                response = providerResult
            )
        }

        // -------------------------
        // Final Result
        // -------------------------

        return AiExecutionResult(

            executionId = context.executionId,

            success = true,

            result = providerResult,

            cacheHit = false,

            executionTime = duration,

            providerTokens =

                providerResult.metadata["totalTokens"]

                    ?.toIntOrNull()

                    ?: 0,

            userCredits =

                providerResult.metadata["credits"]

                    ?.toIntOrNull()

                    ?: 0,

            estimatedCost =

                providerResult.metadata["cost"]

                    ?.toDoubleOrNull()

                    ?: 0.0
        )
    }
}