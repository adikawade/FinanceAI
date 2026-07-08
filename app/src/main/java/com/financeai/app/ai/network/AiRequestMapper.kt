package com.financeai.app.ai.network

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiProviderConfig

/**
 * Converts FinanceAI requests into
 * provider-specific request payloads.
 */
interface AiRequestMapper {

    /**
     * Builds provider request body.
     */
    fun mapRequest(
        config: AiProviderConfig,
        request: AiAnalysisRequest
    ): String

    /**
     * Builds HTTP headers.
     */
    fun mapHeaders(
        config: AiProviderConfig
    ): Map<String, String>

    /**
     * Returns provider endpoint.
     */
    fun endpoint(
        config: AiProviderConfig
    ): String
}