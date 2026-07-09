package com.financeai.app.ai.orchestrator

import java.time.Instant
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Central logger for AI execution pipeline.
 *
 * Used by:
 * - Debug
 * - Admin Dashboard
 * - Analytics
 * - Crash Investigation
 *
 * Logging backend can be replaced later
 * without changing orchestrator code.
 */
class AiPipelineLogger {

    private val logs = CopyOnWriteArrayList<AiPipelineLog>()

    /**
     * Writes an information log.
     */
    fun info(

        executionId: String,

        stage: AiPipelineStage,

        message: String,

        metadata: Map<String, String> = emptyMap()

    ) {

        append(

            level = AiLogLevel.INFO,

            executionId = executionId,

            stage = stage,

            message = message,

            metadata = metadata
        )
    }

    /**
     * Writes a warning log.
     */
    fun warning(

        executionId: String,

        stage: AiPipelineStage,

        message: String,

        metadata: Map<String, String> = emptyMap()

    ) {

        append(

            level = AiLogLevel.WARNING,

            executionId = executionId,

            stage = stage,

            message = message,

            metadata = metadata
        )
    }

    /**
     * Writes an error log.
     */
    fun error(

        executionId: String,

        stage: AiPipelineStage,

        message: String,

        throwable: Throwable? = null,

        metadata: Map<String, String> = emptyMap()

    ) {

        val mergedMetadata = metadata.toMutableMap()

        throwable?.let {

            mergedMetadata["exception"] =
                it::class.java.simpleName

            mergedMetadata["cause"] =
                (it.message ?: "Unknown")
        }

        append(

            level = AiLogLevel.ERROR,

            executionId = executionId,

            stage = stage,

            message = message,

            metadata = mergedMetadata
        )
    }

    /**
     * Returns all logs.
     */
    fun all(): List<AiPipelineLog> {

        return logs.toList()
    }

    /**
     * Returns logs of one execution.
     */
    fun executionLogs(

        executionId: String

    ): List<AiPipelineLog> {

        return logs.filter {

            it.executionId == executionId

        }
    }

    /**
     * Removes all logs.
     */
    fun clear() {

        logs.clear()
    }

    /**
     * Appends new log.
     */
    private fun append(

        level: AiLogLevel,

        executionId: String,

        stage: AiPipelineStage,

        message: String,

        metadata: Map<String, String>

    ) {

        logs.add(

            AiPipelineLog(

                timestamp = Instant.now(),

                level = level,

                executionId = executionId,

                stage = stage,

                message = message,

                metadata = metadata
            )
        )
    }
}

/**
 * One pipeline log.
 */
data class AiPipelineLog(

    val timestamp: Instant,

    val level: AiLogLevel,

    val executionId: String,

    val stage: AiPipelineStage,

    val message: String,

    val metadata: Map<String, String>
)

/**
 * Log severity.
 */
enum class AiLogLevel {

    INFO,

    WARNING,

    ERROR
}