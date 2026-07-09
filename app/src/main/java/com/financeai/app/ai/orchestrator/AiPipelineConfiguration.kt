package com.financeai.app.ai.orchestrator

import java.time.Duration

/**
 * Runtime configuration for the AI execution pipeline.
 *
 * Every value in this configuration is intended to be
 * loaded dynamically from the Admin Panel.
 *
 * No business values should be hardcoded in the
 * orchestrator itself.
 */
data class AiPipelineConfiguration(

    /**
     * Enable complete pipeline.
     */
    val enabled: Boolean = true,

    /**
     * Enable cache lookup.
     */
    val cacheEnabled: Boolean = true,

    /**
     * Save successful responses into cache.
     */
    val cacheWriteEnabled: Boolean = true,

    /**
     * Enable execution tracing.
     */
    val tracingEnabled: Boolean = true,

    /**
     * Enable pipeline metrics.
     */
    val metricsEnabled: Boolean = true,

    /**
     * Enable structured logging.
     */
    val loggingEnabled: Boolean = true,

    /**
     * Enable automatic retry.
     */
    val retryEnabled: Boolean = true,

    /**
     * Maximum retry count.
     */
    val maxRetries: Int = 3,

    /**
     * Delay before retry.
     */
    val retryDelay: Duration = Duration.ofSeconds(2),

    /**
     * Maximum execution timeout.
     */
    val executionTimeout: Duration =
        Duration.ofSeconds(60),

    /**
     * Allow provider fallback.
     */
    val providerFallbackEnabled: Boolean = true,

    /**
     * Enable cancellation support.
     */
    val cancellationEnabled: Boolean = true,

    /**
     * Execute usage tracking.
     */
    val usageTrackingEnabled: Boolean = true,

    /**
     * Execute analytics.
     */
    val analyticsEnabled: Boolean = true,

    /**
     * Execute health checks.
     */
    val healthCheckEnabled: Boolean = true,

    /**
     * Enable pipeline validation.
     */
    val validationEnabled: Boolean = true
) {

    /**
     * Returns true if retry is allowed.
     */
    fun canRetry(
        currentAttempt: Int
    ): Boolean {

        return retryEnabled &&
                currentAttempt < maxRetries
    }

    /**
     * Returns true if pipeline logging
     * should be executed.
     */
    fun shouldLog(): Boolean {

        return loggingEnabled
    }

    /**
     * Returns true if metrics
     * collection is enabled.
     */
    fun shouldCollectMetrics(): Boolean {

        return metricsEnabled
    }

    /**
     * Returns true if execution
     * tracing is enabled.
     */
    fun shouldTrace(): Boolean {

        return tracingEnabled
    }
}