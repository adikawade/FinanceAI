package com.financeai.app.ai.model

import java.time.Instant
import java.util.UUID

/**
 * Runtime context for every AI request.
 *
 * This object carries all metadata required by the AI Engine.
 * It is shared across SMS, Tax, Investment, Budget,
 * Financial Health, Chat and future AI features.
 *
 * No feature should directly pass user/environment data.
 */
data class AiRequestContext(

    /**
     * Unique request identifier.
     */
    val requestId: String = UUID.randomUUID().toString(),

    /**
     * User identifier.
     */
    val userId: String,

    /**
     * User subscription plan.
     *
     * Examples:
     * FREE
     * PREMIUM
     * PRO
     * ENTERPRISE
     */
    val userPlan: String,

    /**
     * AI feature requesting analysis.
     */
    val feature: AiFeature,

    /**
     * Selected language.
     *
     * Example:
     * en
     * hi
     */
    val language: String = "en",

    /**
     * User currency.
     */
    val currency: String = "INR",

    /**
     * User country.
     */
    val country: String = "IN",

    /**
     * Device identifier.
     */
    val deviceId: String = "",

    /**
     * Current session identifier.
     */
    val sessionId: String = "",

    /**
     * Request timestamp.
     */
    val timestamp: Instant = Instant.now(),

    /**
     * Whether device is offline.
     */
    val offlineMode: Boolean = false,

    /**
     * Whether debug mode is enabled.
     */
    val debugMode: Boolean = false,

    /**
     * Additional runtime metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)