package com.financeai.app.ai.orchestrator

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

/**
 * Coordinates execution workflow for the AI Engine.
 *
 * Responsibilities:
 * - Register workflows
 * - Track workflow state
 * - Prevent duplicate execution
 * - Maintain execution history
 *
 * Business logic is executed by the Orchestrator.
 * This class only manages workflow lifecycle.
 */
class AiWorkflowManager {

    private val mutex = Mutex()

    private val workflows =
        ConcurrentHashMap<String, WorkflowRecord>()

    /**
     * Starts workflow.
     *
     * Returns false if workflow
     * is already running.
     */
    suspend fun start(

        executionId: String

    ): Boolean {

        return mutex.withLock {

            if (workflows.containsKey(executionId)) {

                return@withLock false
            }

            workflows[executionId] = WorkflowRecord(

                executionId = executionId,

                state = AiExecutionState.CREATED,

                startedAt = Instant.now()
            )

            true
        }
    }

    /**
     * Updates workflow state.
     */
    suspend fun updateState(

        executionId: String,

        state: AiExecutionState

    ) {

        mutex.withLock {

            val current =

                workflows[executionId]

                    ?: return

            workflows[executionId] =

                current.copy(

                    state = state,

                    updatedAt = Instant.now()
                )
        }
    }

    /**
     * Marks workflow completed.
     */
    suspend fun complete(

        executionId: String

    ) {

        mutex.withLock {

            val current =

                workflows[executionId]

                    ?: return

            workflows[executionId] =

                current.copy(

                    state = AiExecutionState.COMPLETED,

                    finishedAt = Instant.now(),

                    updatedAt = Instant.now()
                )
        }
    }

    /**
     * Marks workflow failed.
     */
    suspend fun fail(

        executionId: String,

        reason: String

    ) {

        mutex.withLock {

            val current =

                workflows[executionId]

                    ?: return

            workflows[executionId] =

                current.copy(

                    state = AiExecutionState.FAILED,

                    failureReason = reason,

                    finishedAt = Instant.now(),

                    updatedAt = Instant.now()
                )
        }
    }

    /**
     * Removes workflow.
     */
    suspend fun remove(

        executionId: String

    ) {

        mutex.withLock {

            workflows.remove(executionId)
        }
    }

    /**
     * Returns workflow.
     */
    fun get(

        executionId: String

    ): WorkflowRecord? {

        return workflows[executionId]
    }

    /**
     * Returns active workflows.
     */
    fun active(): List<WorkflowRecord> {

        return workflows.values

            .filter {

                !it.state.isFinished()

            }
    }

    /**
     * Returns workflow count.
     */
    fun count(): Int {

        return workflows.size
    }

    /**
     * Clears manager.
     */
    suspend fun clear() {

        mutex.withLock {

            workflows.clear()
        }
    }
}

/**
 * Workflow runtime record.
 */
data class WorkflowRecord(

    val executionId: String,

    val state: AiExecutionState,

    val startedAt: Instant,

    val updatedAt: Instant = Instant.now(),

    val finishedAt: Instant? = null,

    val failureReason: String? = null
)