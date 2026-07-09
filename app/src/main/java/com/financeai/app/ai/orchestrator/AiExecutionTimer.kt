package com.financeai.app.ai.orchestrator

import java.time.Duration
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

/**
 * Measures execution time of the complete
 * AI orchestration pipeline and individual stages.
 *
 * Thread-safe implementation.
 */
class AiExecutionTimer {

    private val stageStartTimes =
        ConcurrentHashMap<AiPipelineStage, Instant>()

    private val stageDurations =
        ConcurrentHashMap<AiPipelineStage, Duration>()

    private val executionStartedAt =
        Instant.now()

    @Volatile
    private var executionCompletedAt: Instant? = null

    /**
     * Starts timing for a stage.
     */
    fun startStage(

        stage: AiPipelineStage

    ) {

        stageStartTimes[stage] = Instant.now()
    }

    /**
     * Stops timing for a stage.
     */
    fun finishStage(

        stage: AiPipelineStage

    ) {

        val startedAt = stageStartTimes[stage]
            ?: return

        stageDurations[stage] = Duration.between(

            startedAt,

            Instant.now()

        )
    }

    /**
     * Marks execution completed.
     */
    fun finishExecution() {

        executionCompletedAt = Instant.now()
    }

    /**
     * Returns stage duration.
     */
    fun stageDuration(

        stage: AiPipelineStage

    ): Duration {

        return stageDurations[stage]
            ?: Duration.ZERO
    }

    /**
     * Returns execution duration.
     */
    fun executionDuration(): Duration {

        val end = executionCompletedAt
            ?: Instant.now()

        return Duration.between(

            executionStartedAt,

            end

        )
    }

    /**
     * Returns all stage durations.
     */
    fun stageMetrics(): Map<AiPipelineStage, Duration> {

        return stageDurations.toMap()
    }

    /**
     * Clears collected metrics.
     */
    fun reset() {

        stageStartTimes.clear()

        stageDurations.clear()

        executionCompletedAt = null
    }

    /**
     * Returns execution summary.
     */
    fun summary(): AiExecutionTimingSummary {

        return AiExecutionTimingSummary(

            totalExecutionTime = executionDuration(),

            stages = stageMetrics()
        )
    }
}

/**
 * Execution timing summary.
 */
data class AiExecutionTimingSummary(

    /**
     * Complete execution duration.
     */
    val totalExecutionTime: Duration,

    /**
     * Individual stage timings.
     */
    val stages: Map<AiPipelineStage, Duration>
)