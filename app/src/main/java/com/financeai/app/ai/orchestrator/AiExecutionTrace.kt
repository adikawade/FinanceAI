package com.financeai.app.ai.orchestrator

import java.time.Duration
import java.time.Instant
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Complete execution trace of one AI request.
 *
 * Used by:
 * - Admin Dashboard
 * - Debug Logs
 * - Analytics
 * - AI Metrics
 */
class AiExecutionTrace(

    val traceId: String = UUID.randomUUID().toString(),

    val executionId: String

) {

    private val events = CopyOnWriteArrayList<TraceEvent>()

    private val startedAt = Instant.now()

    @Volatile
    private var finishedAt: Instant? = null

    /**
     * Adds pipeline event.
     */
    fun addEvent(

        stage: AiPipelineStage,

        message: String,

        metadata: Map<String, String> = emptyMap()

    ) {

        events.add(

            TraceEvent(

                stage = stage,

                timestamp = Instant.now(),

                message = message,

                metadata = metadata

            )
        )
    }

    /**
     * Marks execution completed.
     */
    fun finish() {

        finishedAt = Instant.now()
    }

    /**
     * Total execution duration.
     */
    fun duration(): Duration {

        val end = finishedAt ?: Instant.now()

        return Duration.between(

            startedAt,

            end

        )
    }

    /**
     * Returns immutable event list.
     */
    fun events(): List<TraceEvent> {

        return events.toList()
    }

    /**
     * Returns latest event.
     */
    fun latestEvent(): TraceEvent? {

        return events.lastOrNull()
    }

    /**
     * Returns true if trace finished.
     */
    fun isFinished(): Boolean {

        return finishedAt != null
    }

    /**
     * Returns stage count.
     */
    fun stageCount(): Int {

        return events.size
    }

    /**
     * Returns summary.
     */
    fun summary(): AiExecutionTraceSummary {

        return AiExecutionTraceSummary(

            traceId = traceId,

            executionId = executionId,

            totalStages = stageCount(),

            duration = duration(),

            completed = isFinished()
        )
    }
}

/**
 * One pipeline event.
 */
data class TraceEvent(

    val stage: AiPipelineStage,

    val timestamp: Instant,

    val message: String,

    val metadata: Map<String, String>
)

/**
 * Trace summary.
 */
data class AiExecutionTraceSummary(

    val traceId: String,

    val executionId: String,

    val totalStages: Int,

    val duration: Duration,

    val completed: Boolean
)