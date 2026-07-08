package com.financeai.app.ai.model

/**
 * Universal response returned by the AI Engine.
 *
 * Every AI feature in FinanceAI should return this model.
 *
 * Examples:
 * - SMS Analysis
 * - Tax Suggestions
 * - Investment Advice
 * - Budget Recommendation
 * - Financial Health
 * - AI Chat
 */
data class AiAnalysisResult(

    /**
     * Whether AI successfully completed the request.
     */
    val success: Boolean,

    /**
     * AI Predictions.
     */
    val predictions: List<AiPrediction> = emptyList(),

    /**
     * Confidence evaluation.
     */
    val confidence: AiConfidence,

    /**
     * Human readable summary.
     */
    val summary: String = "",

    /**
     * AI explanation.
     */
    val explanation: String = "",

    /**
     * Name of the provider.
     *
     * Example:
     * GitHub Models
     * OpenAI
     * Gemini
     */
    val provider: String = "",

    /**
     * Model used.
     *
     * Example:
     * GPT-5
     * DeepSeek-V3
     * Llama-4
     */
    val model: String = "",

    /**
     * Processing time in milliseconds.
     */
    val processingTimeMs: Long = 0L,

    /**
     * Estimated token usage.
     */
    val tokenUsage: Int = 0,

    /**
     * Estimated request cost.
     *
     * Zero for free providers.
     */
    val estimatedCost: Double = 0.0,

    /**
     * Additional metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)