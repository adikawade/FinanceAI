package com.financeai.app.ai.network

import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.model.AiProviderConfig

/**
 * Converts provider responses into
 * FinanceAI models.
 */
interface AiResponseMapper {

    /**
     * Maps provider JSON response
     * into AiAnalysisResult.
     */
    fun mapResponse(
        config: AiProviderConfig,
        response: String
    ): AiAnalysisResult
}