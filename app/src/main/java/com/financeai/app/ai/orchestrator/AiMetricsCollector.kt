package com.financeai.app.ai.orchestrator

import java.time.Duration
import java.util.concurrent.atomic.AtomicLong

/**
 * Collects runtime metrics for the AI Orchestrator.
 *
 * Metrics are used by:
 * - Admin Dashboard
 * - AI Analytics
 * - Provider Monitoring
 * - Health Monitoring
 *
 * This class only collects metrics.
 * Persistence is handled elsewhere.
 */
class AiMetricsCollector {

    private val totalExecutions = AtomicLong(0)

    private val successfulExecutions = AtomicLong(0)

    private val failedExecutions = AtomicLong(0)

    private val cancelledExecutions = AtomicLong(0)

    private val cacheHits = AtomicLong(0)

    private val cacheMisses = AtomicLong(0)

    private val totalExecutionTimeMs = AtomicLong(0)

    /**
     * Records successful execution.
     */
    fun recordSuccess(

        duration: Duration,

        cacheHit: Boolean

    ) {

        totalExecutions.incrementAndGet()

        successfulExecutions.incrementAndGet()

        totalExecutionTimeMs.addAndGet(

            duration.toMillis()

        )

        if (cacheHit) {

            cacheHits.incrementAndGet()

        } else {

            cacheMisses.incrementAndGet()

        }
    }

    /**
     * Records failed execution.
     */
    fun recordFailure(

        duration: Duration

    ) {

        totalExecutions.incrementAndGet()

        failedExecutions.incrementAndGet()

        totalExecutionTimeMs.addAndGet(

            duration.toMillis()

        )
    }

    /**
     * Records cancelled execution.
     */
    fun recordCancelled() {

        totalExecutions.incrementAndGet()

        cancelledExecutions.incrementAndGet()
    }

    /**
     * Returns metrics snapshot.
     */
    fun snapshot(): AiMetricsSnapshot {

        val executions = totalExecutions.get()

        val average =

            if (executions == 0L) {

                0L

            } else {

                totalExecutionTimeMs.get() / executions

            }

        return AiMetricsSnapshot(

            totalExecutions = executions,

            successfulExecutions =

                successfulExecutions.get(),

            failedExecutions =

                failedExecutions.get(),

            cancelledExecutions =

                cancelledExecutions.get(),

            cacheHits =

                cacheHits.get(),

            cacheMisses =

                cacheMisses.get(),

            averageExecutionTimeMs = average
        )
    }

    /**
     * Clears metrics.
     */
    fun reset() {

        totalExecutions.set(0)

        successfulExecutions.set(0)

        failedExecutions.set(0)

        cancelledExecutions.set(0)

        cacheHits.set(0)

        cacheMisses.set(0)

        totalExecutionTimeMs.set(0)
    }
}

/**
 * Immutable metrics snapshot.
 */
data class AiMetricsSnapshot(

    val totalExecutions: Long,

    val successfulExecutions: Long,

    val failedExecutions: Long,

    val cancelledExecutions: Long,

    val cacheHits: Long,

    val cacheMisses: Long,

    val averageExecutionTimeMs: Long

) {

    /**
     * Success percentage.
     */
    val successRate: Double

        get() =

            if (totalExecutions == 0L) {

                0.0

            } else {

                successfulExecutions.toDouble() /
                        totalExecutions.toDouble()
            }

    /**
     * Cache hit percentage.
     */
    val cacheHitRate: Double

        get() =

            val total =

                cacheHits + cacheMisses

            if (total == 0L) {

                0.0

            } else {

                cacheHits.toDouble() /
                        total.toDouble()
            }
}