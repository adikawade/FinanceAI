package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiFeature
import com.financeai.app.ai.model.AiModel
import com.financeai.app.ai.model.AiProvider
import java.time.Instant
import java.util.UUID

/**
 * Immutable execution context for a single AI request.
 *
 * This object travels through the complete AI pipeline.
 * It contains every runtime value required by the
 * orchestrator and its sub-components.
 */
data class AiExecutionContext(

    /**
     * Unique execution identifier.
     */
    val executionId: String = UUID.randomUUID().toString(),

    /**
     * Current authenticated user.
     */
    val userId: String,

    /**
     * Requested AI feature.
     */
    val feature: AiFeature,

    /**
     * Original AI request.
     */
    val request: AiAnalysisRequest,

    /**
     * Selected AI provider.
     */
    val provider: AiProvider,

    /**
     * Selected AI model.
     */
    val model: AiModel,

    /**
     * Whether cache lookup is enabled.
     */
    val cacheEnabled: Boolean = true,

    /**
     * Whether usage tracking is enabled.
     */
    val usageTrackingEnabled: Boolean = true,

    /**
     * Whether response should be cached.
     */
    val saveToCache: Boolean = true,

    /**
     * Request priority.
     */
    val priority: Int = 0,

    /**
     * Time when execution started.
     */
    val startedAt: Instant = Instant.now(),

    /**
     * Optional metadata.
     */
    val metadata: Map<String, String> = emptyMap()
)