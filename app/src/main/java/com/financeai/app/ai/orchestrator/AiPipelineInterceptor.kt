package com.financeai.app.ai.orchestrator

/**
 * Pipeline interceptor.
 *
 * Interceptors can observe, modify or stop
 * pipeline execution before and after every stage.
 *
 * Examples:
 * - Logging
 * - Metrics
 * - Admin Rules
 * - Analytics
 * - Security
 * - Rate Limiting
 */
interface AiPipelineInterceptor {

    /**
     * Unique interceptor id.
     */
    val id: String

    /**
     * Execution priority.
     *
     * Higher value executes first.
     */
    val priority: Int

    /**
     * Returns true if interceptor
     * is enabled.
     */
    fun isEnabled(): Boolean = true

    /**
     * Called before stage execution.
     */
    suspend fun beforeStage(

        context: AiExecutionContext,

        stage: AiPipelineStage

    )

    /**
     * Called after successful stage execution.
     */
    suspend fun afterStage(

        context: AiExecutionContext,

        stage: AiPipelineStage,

        result: AiExecutionResult?

    )

    /**
     * Called when stage fails.
     */
    suspend fun onError(

        context: AiExecutionContext,

        stage: AiPipelineStage,

        exception: Throwable

    )

    /**
     * Called when entire execution
     * has finished.
     */
    suspend fun onCompleted(

        context: AiExecutionContext,

        result: AiExecutionResult

    )

    /**
     * Called when execution
     * gets cancelled.
     */
    suspend fun onCancelled(

        context: AiExecutionContext

    )

    /**
     * Determines whether interceptor
     * should execute for current stage.
     */
    fun supports(

        stage: AiPipelineStage

    ): Boolean = true
}