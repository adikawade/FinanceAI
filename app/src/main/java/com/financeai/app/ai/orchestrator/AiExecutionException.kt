package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.model.AiProvider

/**
 * Base exception for every AI orchestration failure.
 *
 * All exceptions thrown inside the AI pipeline should
 * inherit from this class.
 */
open class AiExecutionException(

    override val message: String,

    /**
     * Current pipeline stage.
     */
    val stage: AiPipelineStage,

    /**
     * AI provider involved.
     */
    val provider: AiProvider? = null,

    /**
     * Error code used for analytics,
     * logging and Admin Dashboard.
     */
    val errorCode: String = "AI_EXECUTION_ERROR",

    /**
     * Whether this operation can be retried.
     */
    val retryable: Boolean = false,

    /**
     * Root cause.
     */
    override val cause: Throwable? = null

) : Exception(message, cause) {

    /**
     * Returns structured metadata for
     * logging and crash reporting.
     */
    fun toMetadata(): Map<String, String> {

        return mapOf(

            "errorCode" to errorCode,

            "stage" to stage.name,

            "provider" to (provider?.name ?: "UNKNOWN"),

            "retryable" to retryable.toString(),

            "exception" to this::class.java.simpleName
        )
    }
}

/**
 * Thrown when user is not allowed
 * to access an AI feature.
 */
class AiAccessDeniedException(

    message: String

) : AiExecutionException(

    message = message,

    stage = AiPipelineStage.ACCESS_CONTROL,

    errorCode = "AI_ACCESS_DENIED",

    retryable = false
)

/**
 * Thrown when provider execution fails.
 */
class AiProviderException(

    provider: AiProvider,

    message: String,

    retryable: Boolean,

    cause: Throwable? = null

) : AiExecutionException(

    message = message,

    provider = provider,

    stage = AiPipelineStage.PROVIDER_EXECUTION,

    errorCode = "AI_PROVIDER_ERROR",

    retryable = retryable,

    cause = cause
)

/**
 * Thrown when cache operations fail.
 */
class AiCacheException(

    message: String,

    cause: Throwable? = null

) : AiExecutionException(

    message = message,

    stage = AiPipelineStage.CACHE_UPDATE,

    errorCode = "AI_CACHE_ERROR",

    retryable = true,

    cause = cause
)