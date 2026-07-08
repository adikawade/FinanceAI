package com.financeai.app.ai.model

/**
 * Represents AI confidence evaluation.
 *
 * This class is used by every AI feature
 * to decide whether a prediction should be
 * accepted automatically or verified.
 */
data class AiConfidence(

    /**
     * Confidence score.
     *
     * Range:
     * 0.0 - 1.0
     */
    val score: Float,

    /**
     * Confidence level.
     */
    val level: ConfidenceLevel,

    /**
     * Whether user verification is required.
     */
    val requiresVerification: Boolean,

    /**
     * Explanation.
     */
    val reason: String = ""
)

/**
 * Universal confidence levels.
 */
enum class ConfidenceLevel {

    VERY_LOW,

    LOW,

    MEDIUM,

    HIGH,

    VERY_HIGH
}