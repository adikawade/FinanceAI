package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.cache.AiCacheManager
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.provider.AiProviderManager
import com.financeai.app.ai.usage.AiUsageTracker
import java.time.Duration

/**
 * Executes a single AI request.
 *
 * Responsibilities:
 * - Cache lookup
 * - Provider execution
 * - Usage tracking
 * - Cache update
 */
class AiRequestExecutor(

    private val providerManager: AiProviderManager,

    private val cacheManager: AiCacheManager,

    private val usageTracker: AiUsageTracker

) {

    suspend fun execute(

        context: AiExecutionContext

    ): AiExecutionResult {

        if (context.cacheEnabled) {

            cacheManager.get(

                context.request

            )?.let { cached ->

                return AiExecutionResult(

                    executionId = context.executionId,

                    result = cached,

                    success = true,

                    cacheHit = true,

                    executionTime = Duration.ZERO,

                    providerTokens = cached.tokenUsage,

                    userCredits = 0,

                    estimatedCost = cached.estimatedCost
                )
            }
        }

        val started = System.nanoTime()

        val providerResult: AiAnalysisResult =

            providerManager.execute(

                context.request,

                context.provider,

                context.model
            )

        val duration =

            Duration.ofNanos(

                System.nanoTime() - started
            )

        if (context.usageTrackingEnabled) {

            usageTracker.record(

                context,

                providerResult
            )
        }

        if (context.saveToCache) {

            cacheManager.put(

                context.request,

                providerResult
            )
        }

        return AiExecutionResult(

            executionId = context.executionId,

            result = providerResult,

            success = providerResult.success,

            cacheHit = false,

            executionTime = duration,

            providerTokens = providerResult.tokenUsage,

            userCredits = 0,

            estimatedCost = providerResult.estimatedCost
        )
    }
}