package com.financeai.app.ai.network

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.model.AiProviderConfig

/**
 * Generic AI API client.
 *
 * All AI providers (GitHub, OpenAI, Gemini, Groq, etc.)
 * use this contract instead of implementing networking directly.
 */
interface AiApiClient {

    /**
     * Executes an AI request.
     */
    suspend fun execute(

        config: AiProviderConfig,

        request: AiAnalysisRequest

    ): AiAnalysisResult
}