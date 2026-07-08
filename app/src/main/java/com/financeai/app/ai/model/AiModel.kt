package com.financeai.app.ai.model

/**
 * Represents an AI model.
 *
 * Models are selected by the Admin Panel.
 * They are not hardcoded in the application.
 */
data class AiModel(

    /**
     * Unique model id.
     *
     * Example:
     * gpt-5
     * deepseek-v3
     */
    val id: String,

    /**
     * Display name.
     */
    val name: String,

    /**
     * Provider.
     */
    val provider: AiProvider,

    /**
     * Whether the model is enabled.
     */
    val enabled: Boolean = true,

    /**
     * Supports image input.
     */
    val supportsVision: Boolean = false,

    /**
     * Supports function/tool calling.
     */
    val supportsTools: Boolean = false,

    /**
     * Maximum context window.
     */
    val maxContextTokens: Int = 0
)