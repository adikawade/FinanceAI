package com.financeai.app.ai.orchestrator

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Thread-safe cancellation token used by the AI Orchestrator.
 *
 * Every long running AI execution should periodically
 * check this token before continuing execution.
 */
class AiCancellationToken {

    private val cancelled = AtomicBoolean(false)

    private val mutex = Mutex()

    @Volatile
    private var cancelledAt: Instant? = null

    @Volatile
    private var reason: String? = null

    /**
     * Cancels current execution.
     *
     * Returns true if cancellation happened
     * for the first time.
     */
    suspend fun cancel(

        reason: String = "Cancelled"

    ): Boolean {

        return mutex.withLock {

            if (cancelled.get()) {

                return@withLock false
            }

            cancelled.set(true)

            cancelledAt = Instant.now()

            this.reason = reason

            true
        }
    }

    /**
     * Returns true if execution
     * has been cancelled.
     */
    fun isCancelled(): Boolean {

        return cancelled.get()
    }

    /**
     * Throws cancellation exception
     * if execution has been cancelled.
     */
    fun throwIfCancelled() {

        if (cancelled.get()) {

            throw AiExecutionCancelledException(

                reason = reason ?: "Cancelled"

            )
        }
    }

    /**
     * Cancellation timestamp.
     */
    fun cancelledAt(): Instant? {

        return cancelledAt
    }

    /**
     * Cancellation reason.
     */
    fun cancellationReason(): String? {

        return reason
    }

    /**
     * Returns current status.
     */
    fun status(): CancellationStatus {

        return CancellationStatus(

            cancelled = cancelled.get(),

            cancelledAt = cancelledAt,

            reason = reason
        )
    }
}

/**
 * Cancellation state.
 */
data class CancellationStatus(

    val cancelled: Boolean,

    val cancelledAt: Instant?,

    val reason: String?
)

/**
 * Exception thrown when execution
 * is cancelled.
 */
class AiExecutionCancelledException(

    reason: String

) : AiExecutionException(

    message = reason,

    stage = AiPipelineStage.CANCELLED,

    errorCode = "AI_EXECUTION_CANCELLED",

    retryable = false
)