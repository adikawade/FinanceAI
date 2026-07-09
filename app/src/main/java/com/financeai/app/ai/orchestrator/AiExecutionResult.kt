package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.model.AiAnalysisResult
import java.time.Duration
import java.time.Instant

/**
 * Final result returned by the AI Orchestrator.
 *
 * This object contains everything required by the
 * caller after an AI request finishes.
 */
data class AiExecutionResult(

    /**
     * Execution identifier.
     */
    val executionId: String,

    /**
     * Original AI result.
     */
    val result: AiAnalysisResult? = null,

    /**
     * Whether execution completed successfully.
     */
    val success: Boolean,

    /**
     * Whether response came from cache.
     */
    val cacheHit: Boolean = false,

    /**
     * Provider execution duration.
     */
    val executionTime: Duration,

    /**
     * Error message if execution failed.
     */
    val errorMessage: String? = null,

    /**
     * Exception class name.
     */
    val errorType: String? = null,

    /**
     * Provider tokens consumed.
     */
    val providerTokens: Int = 0,

    /**
     * User credits consumed.
     */
    val userCredits: Int = 0,

    /**
     * Estimated provider cost.
     */
    val estimatedCost: Double = 0.0,

    /**
     * Response timestamp.
     */
    val completedAt: Instant = Instant.now(),

    /**
     * Additional execution metadata.
     */
    val metadata: Map<String, String> = emptyMap()
) {

    /**
     * Returns true if execution failed.
     */
    val failed: Boolean
        get() = !success

    /**
     * Returns true if execution succeeded.
     */
    val completed: Boolean
        get() = success
}