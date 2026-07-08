package com.financeai.app.ai.model

/**
 * Represents a single AI prediction.
 *
 * One request can return multiple predictions.
 *
 * Example:
 * FOOD (92%)
 * SHOPPING (5%)
 * OTHER (3%)
 */
data class AiPrediction(

    /**
     * Prediction label.
     *
     * Examples:
     * FOOD
     * SHOPPING
     * TAX_SAVING
     * SIP
     */
    val label: String,

    /**
     * Confidence score.
     *
     * Range:
     * 0.0 - 1.0
     */
    val confidence: Float,

    /**
     * Human readable explanation.
     *
     * Example:
     * Merchant matches restaurant database.
     */
    val reason: String = "",

    /**
     * Extra metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)