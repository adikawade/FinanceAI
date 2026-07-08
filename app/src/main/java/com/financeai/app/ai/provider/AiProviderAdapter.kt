package com.financeai.app.ai.provider

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.model.AiModel
import com.financeai.app.ai.model.AiProvider
import com.financeai.app.ai.model.AiProviderConfig

/**
 * Base contract for every AI provider.
 *
 * Every AI provider implementation must implement this interface.
 */
interface AiProviderAdapter {

    /**
     * Provider type.
     */
    val provider: AiProvider

    /**
     * Runtime configuration.
     */
    val config: AiProviderConfig

    /**
     * Returns supported models.
     */
    suspend fun getAvailableModels(): List<AiModel>

    /**
     * Analyze request.
     */
    suspend fun analyze(
        request: AiAnalysisRequest
    ): AiAnalysisResult

    /**
     * Checks provider availability.
     */
    suspend fun isAvailable(): Boolean

    /**
     * Lightweight health check.
     */
    suspend fun healthCheck(): Boolean

    /**
     * Validate configuration.
     */
    suspend fun validateConfiguration(): Boolean

    /**
     * Estimate request cost.
     */
    suspend fun estimateCost(
        request: AiAnalysisRequest
    ): Double

    /**
     * Estimate token usage.
     */
    suspend fun estimateTokens(
        request: AiAnalysisRequest
    ): Int
}