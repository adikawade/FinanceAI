package com.financeai.app.ai.orchestrator

/**
 * Represents every execution stage of the AI pipeline.
 *
 * Each AI request moves through these stages
 * sequentially.
 */
enum class AiPipelineStage(

    /**
     * Human readable stage title.
     */
    val displayName: String

) {

    /**
     * Request accepted.
     */
    CREATED("Created"),

    /**
     * Rule validation.
     */
    RULE_ENGINE("Rule Engine"),

    /**
     * Permission validation.
     */
    ACCESS_CONTROL("Access Control"),

    /**
     * User plan verification.
     */
    PLAN_VALIDATION("Plan Validation"),

    /**
     * AI credit verification.
     */
    CREDIT_VALIDATION("Credit Validation"),

    /**
     * Cache lookup.
     */
    CACHE_LOOKUP("Cache Lookup"),

    /**
     * Provider selection.
     */
    PROVIDER_SELECTION("Provider Selection"),

    /**
     * Model selection.
     */
    MODEL_SELECTION("Model Selection"),

    /**
     * AI request execution.
     */
    PROVIDER_EXECUTION("Provider Execution"),

    /**
     * AI response received.
     */
    RESPONSE_RECEIVED("Response Received"),

    /**
     * Cache update.
     */
    CACHE_UPDATE("Cache Update"),

    /**
     * Usage tracking.
     */
    USAGE_TRACKING("Usage Tracking"),

    /**
     * Metrics collection.
     */
    METRICS_COLLECTION("Metrics Collection"),

    /**
     * Execution completed.
     */
    COMPLETED("Completed"),

    /**
     * Execution failed.
     */
    FAILED("Failed"),

    /**
     * Execution cancelled.
     */
    CANCELLED("Cancelled");

    /**
     * Returns true if stage is terminal.
     */
    fun isTerminal(): Boolean {

        return this == COMPLETED ||
                this == FAILED ||
                this == CANCELLED
    }

    /**
     * Returns true if provider
     * interaction has started.
     */
    fun isProviderStage(): Boolean {

        return this == PROVIDER_SELECTION ||
                this == MODEL_SELECTION ||
                this == PROVIDER_EXECUTION ||
                this == RESPONSE_RECEIVED
    }

    /**
     * Returns true if this stage
     * belongs to pre-processing.
     */
    fun isPreExecutionStage(): Boolean {

        return ordinal <
                PROVIDER_SELECTION.ordinal
    }
}