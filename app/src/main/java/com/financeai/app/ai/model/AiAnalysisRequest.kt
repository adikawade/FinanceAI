package com.financeai.app.ai.model

/**
 * Universal request model for every AI feature.
 *
 * This model is intentionally generic so it can be
 * reused across SMS, Tax, Investment, Budget,
 * Financial Health, OCR and future AI features.
 */
data class AiAnalysisRequest(

    /**
     * Unique feature requesting AI.
     *
     * Examples:
     * SMS
     * TAX
     * INVESTMENT
     * CHAT
     */
    val feature: AiFeature,

    /**
     * Raw content sent to AI.
     */
    val input: String,

    /**
     * Optional structured context.
     *
     * Example:
     * Merchant
     * Amount
     * Category
     * User Profile
     */
    val context: Map<String, String> = emptyMap(),

    /**
     * Preferred model.
     *
     * Null means:
     * Use Admin Panel configuration.
     */
    val preferredModel: String? = null,

    /**
     * Extra runtime options.
     */
    val options: Map<String, String> = emptyMap()
)

enum class AiFeature {

    SMS,

    TAX,

    INVESTMENT,

    BUDGET,

    GOAL,

    FINANCIAL_HEALTH,

    STATEMENT,

    RECEIPT,

    CHAT,

    CA_ASSISTANT
}