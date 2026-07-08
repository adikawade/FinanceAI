package com.financeai.app.ai.network

import com.financeai.app.ai.model.*

/**
 * Handles provider/network errors and converts them
 * into standard FinanceAI responses.
 *
 * Every provider should use this class instead of
 * implementing its own error handling.
 */
object AiErrorHandler {

    fun handleException(
        throwable: Throwable,
        provider: AiProvider,
        model: String = ""
    ): AiAnalysisResult {

        val message = throwable.message ?: "Unknown AI error."

        return AiAnalysisResult(

            success = false,

            predictions = emptyList(),

            confidence = AiConfidence(

                score = 0f,

                level = ConfidenceLevel.VERY_LOW,

                requiresVerification = false,

                reason = message

            ),

            summary = message,

            explanation = message,

            provider = provider.name,

            model = model,

            metadata = mapOf(

                "errorType" to throwable::class.java.simpleName

            )
        )
    }

    /**
     * Returns true if request can be retried.
     */
    fun shouldRetry(
        statusCode: Int
    ): Boolean {

        return when (statusCode) {

            408 -> true

            429 -> true

            in 500..599 -> true

            else -> false
        }
    }

    /**
     * Returns true if fallback provider
     * should be attempted.
     */
    fun shouldFallback(
        statusCode: Int
    ): Boolean {

        return when (statusCode) {

            401 -> true

            403 -> true

            408 -> true

            429 -> true

            in 500..599 -> true

            else -> false
        }
    }

    /**
     * Returns user-friendly message.
     */
    fun messageForStatus(
        statusCode: Int
    ): String {

        return when (statusCode) {

            400 -> "Invalid AI request."

            401 -> "Authentication failed."

            403 -> "Access denied."

            404 -> "AI endpoint not found."

            408 -> "Request timeout."

            429 -> "Rate limit exceeded."

            in 500..599 -> "AI service unavailable."

            else -> "Unexpected AI error."
        }
    }
}