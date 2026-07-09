package com.financeai.app.ai.orchestrator

/**
 * FinanceAI AI Engine Orchestrator.
 *
 * Entry point of the complete AI execution flow.
 *
 * Responsibilities:
 * - Session lifecycle
 * - Workflow lifecycle
 * - Pipeline execution
 * - Cancellation support
 * - Metrics collection
 *
 * Business rules (credits, plans, provider selection,
 * cache etc.) are delegated to dedicated components.
 */
class AiOrchestrator(

    private val sessionManager: AiSessionManager,

    private val workflowManager: AiWorkflowManager,

    private val pipeline: AiExecutionPipeline

) {

    /**
     * Executes one AI request.
     */
    suspend fun execute(

        context: AiExecutionContext

    ): AiExecutionResult {

        sessionManager.createSession(

            userId = context.userId,

            executionId = context.executionId
        )

        workflowManager.start(

            context.executionId
        )

        sessionManager.updateState(

            context.executionId,

            AiExecutionState.EXECUTING
        )

        workflowManager.updateState(

            context.executionId,

            AiExecutionState.EXECUTING
        )

        return try {

            val result = pipeline.execute(

                context
            )

            sessionManager.complete(

                context.executionId
            )

            workflowManager.complete(

                context.executionId
            )

            result

        } catch (

            exception: Throwable

        ) {

            sessionManager.fail(

                context.executionId,

                exception.message ?: "Unknown error"
            )

            workflowManager.fail(

                context.executionId,

                exception.message ?: "Unknown error"
            )

            throw exception

        } finally {

            sessionManager.removeSession(

                context.executionId
            )

            workflowManager.remove(

                context.executionId
            )
        }
    }

    /**
     * Cancels a running execution.
     */
    suspend fun cancel(

        executionId: String,

        reason: String

    ) {

        sessionManager.cancel(

            executionId,

            reason
        )

        workflowManager.fail(

            executionId,

            reason
        )
    }

    /**
     * Returns active execution count.
     */
    fun activeExecutionCount(): Int {

        return sessionManager.activeCount()
    }

    /**
     * Returns active sessions.
     */
    fun activeSessions(): List<AiSession> {

        return sessionManager.activeSessions()
    }

    /**
     * Clears orchestrator state.
     *
     * Intended for testing only.
     */
    suspend fun reset() {

        sessionManager.clear()

        workflowManager.clear()
    }
}