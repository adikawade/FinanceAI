package com.financeai.app.ai.orchestrator

/**
 * Represents the lifecycle state of an AI execution.
 *
 * This state is used by:
 * - AI Orchestrator
 * - Pipeline
 * - Metrics
 * - Admin Dashboard
 * - Execution Logger
 */
enum class AiExecutionState(

    /**
     * Human readable name.
     */
    val displayName: String

) {

    /**
     * Execution created but not started.
     */
    CREATED("Created"),

    /**
     * Waiting inside execution queue.
     */
    QUEUED("Queued"),

    /**
     * Pipeline validation.
     */
    VALIDATING("Validating"),

    /**
     * Waiting for provider.
     */
    WAITING_PROVIDER("Waiting Provider"),

    /**
     * Provider currently processing request.
     */
    EXECUTING("Executing"),

    /**
     * Reading cached response.
     */
    READING_CACHE("Reading Cache"),

    /**
     * Writing cache.
     */
    WRITING_CACHE("Writing Cache"),

    /**
     * Saving usage information.
     */
    SAVING_USAGE("Saving Usage"),

    /**
     * Successfully completed.
     */
    COMPLETED("Completed"),

    /**
     * Cancelled by system or user.
     */
    CANCELLED("Cancelled"),

    /**
     * Execution failed.
     */
    FAILED("Failed"),

    /**
     * Timed out.
     */
    TIMEOUT("Timeout");

    /**
     * Returns true if execution has finished.
     */
    fun isFinished(): Boolean {

        return this == COMPLETED ||
                this == FAILED ||
                this == CANCELLED ||
                this == TIMEOUT
    }

    /**
     * Returns true if provider
     * execution has started.
     */
    fun isRunning(): Boolean {

        return this == EXECUTING ||
                this == WAITING_PROVIDER
    }

    /**
     * Returns true if state
     * allows retry.
     */
    fun canRetry(): Boolean {

        return this == FAILED ||
                this == TIMEOUT
    }

    /**
     * Returns true if execution
     * can be cancelled.
     */
    fun canCancel(): Boolean {

        return this != COMPLETED &&
                this != FAILED &&
                this != CANCELLED &&
                this != TIMEOUT
    }
}