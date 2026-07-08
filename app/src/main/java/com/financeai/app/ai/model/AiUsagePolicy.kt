package com.financeai.app.ai.model

/**
 * Defines AI usage policy for a user plan.
 *
 * This policy is controlled entirely by the
 * Admin Panel and should never be hardcoded.
 */
data class AiUsagePolicy(

    /**
     * Plan identifier.
     *
     * Examples:
     * FREE
     * PREMIUM
     * PRO
     * ENTERPRISE
     */
    val planId: String,

    /**
     * Whether AI is enabled.
     */
    val aiEnabled: Boolean = true,

    /**
     * Maximum AI requests allowed per day.
     */
    val dailyRequestLimit: Int = 20,

    /**
     * Maximum AI requests allowed per month.
     */
    val monthlyRequestLimit: Int = 500,

    /**
     * Maximum input/output tokens per day.
     */
    val dailyTokenLimit: Long = 100_000,

    /**
     * Maximum input/output tokens per month.
     */
    val monthlyTokenLimit: Long = 3_000_000,

    /**
     * Daily AI spending limit.
     */
    val dailyCostLimit: Double = 0.0,

    /**
     * Monthly AI spending limit.
     */
    val monthlyCostLimit: Double = 0.0,

    /**
     * Maximum simultaneous AI requests.
     */
    val maxConcurrentRequests: Int = 2,

    /**
     * Cooldown between requests.
     */
    val cooldownSeconds: Int = 0,

    /**
     * Allowed providers.
     */
    val allowedProviders: Set<AiProvider> = emptySet(),

    /**
     * Allowed models.
     */
    val allowedModels: Set<String> = emptySet(),

    /**
     * Allowed AI features.
     */
    val allowedFeatures: Set<AiFeature> = emptySet(),

    /**
     * Whether fallback is allowed.
     */
    val allowFallback: Boolean = true,

    /**
     * Queue priority.
     *
     * Higher value = higher priority.
     */
    val priority: Int = 0
)