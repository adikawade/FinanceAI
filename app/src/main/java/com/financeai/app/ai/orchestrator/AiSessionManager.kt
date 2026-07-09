package com.financeai.app.ai.orchestrator

import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages active AI execution sessions.
 *
 * Responsibilities:
 * - Create execution session
 * - Track active executions
 * - Close completed sessions
 * - Cancel running sessions
 * - Session lookup
 *
 * This class does NOT execute AI.
 * It only manages execution lifecycle.
 */
class AiSessionManager {

    private val activeSessions =
        ConcurrentHashMap<String, AiSession>()

    /**
     * Creates a new execution session.
     */
    fun createSession(

        userId: String,

        executionId: String = UUID.randomUUID().toString()

    ): AiSession {

        val session = AiSession(

            sessionId = UUID.randomUUID().toString(),

            executionId = executionId,

            userId = userId,

            state = AiExecutionState.CREATED,

            createdAt = Instant.now()
        )

        activeSessions[executionId] = session

        return session
    }

    /**
     * Returns session by execution id.
     */
    fun getSession(

        executionId: String

    ): AiSession? {

        return activeSessions[executionId]
    }

    /**
     * Updates session state.
     */
    fun updateState(

        executionId: String,

        state: AiExecutionState

    ) {

        activeSessions.computeIfPresent(

            executionId

        ) { _, session ->

            session.copy(

                state = state,

                updatedAt = Instant.now()
            )
        }
    }

    /**
     * Completes session.
     */
    fun complete(

        executionId: String

    ) {

        activeSessions.computeIfPresent(

            executionId

        ) { _, session ->

            session.copy(

                state = AiExecutionState.COMPLETED,

                completedAt = Instant.now(),

                updatedAt = Instant.now()
            )
        }
    }

    /**
     * Marks session failed.
     */
    fun fail(

        executionId: String,

        reason: String

    ) {

        activeSessions.computeIfPresent(

            executionId

        ) { _, session ->

            session.copy(

                state = AiExecutionState.FAILED,

                failureReason = reason,

                completedAt = Instant.now(),

                updatedAt = Instant.now()
            )
        }
    }

    /**
     * Cancels session.
     */
    fun cancel(

        executionId: String,

        reason: String

    ) {

        activeSessions.computeIfPresent(

            executionId

        ) { _, session ->

            session.copy(

                state = AiExecutionState.CANCELLED,

                cancellationReason = reason,

                completedAt = Instant.now(),

                updatedAt = Instant.now()
            )
        }
    }

    /**
     * Removes completed session.
     */
    fun removeSession(

        executionId: String

    ) {

        activeSessions.remove(executionId)
    }

    /**
     * Returns all active sessions.
     */
    fun activeSessions(): List<AiSession> {

        return activeSessions.values.toList()
    }

    /**
     * Returns active session count.
     */
    fun activeCount(): Int {

        return activeSessions.size
    }

    /**
     * Clears all sessions.
     */
    fun clear() {

        activeSessions.clear()
    }
}

/**
 * Represents one AI execution session.
 */
data class AiSession(

    val sessionId: String,

    val executionId: String,

    val userId: String,

    val state: AiExecutionState,

    val createdAt: Instant,

    val updatedAt: Instant = Instant.now(),

    val completedAt: Instant? = null,

    val failureReason: String? = null,

    val cancellationReason: String? = null
)