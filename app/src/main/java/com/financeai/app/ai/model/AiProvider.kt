package com.financeai.app.ai.model

/**
 * Supported AI providers.
 *
 * These providers are managed from the Admin Panel.
 * The AI Engine should never hardcode any provider.
 */
enum class AiProvider {

    /**
     * GitHub Models
     */
    GITHUB,

    /**
     * OpenAI
     */
    OPENAI,

    /**
     * Google Gemini
     */
    GEMINI,

    /**
     * Groq
     */
    GROQ,

    /**
     * OpenRouter
     */
    OPENROUTER,

    /**
     * Azure OpenAI
     */
    AZURE_OPENAI,

    /**
     * Local / Offline Models
     */
    LOCAL,

    /**
     * Custom Provider
     */
    CUSTOM
}