package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.model.AiModel
import com.financeai.app.ai.model.AiProvider
import com.financeai.app.ai.model.AiProviderConfig
import com.financeai.app.ai.network.AiApiClient
import com.financeai.app.ai.provider.AiProviderAdapter

/**
 * GitHub Models Provider implementation.
 *
 * All GitHub Models requests are routed through
 * this provider.
 */
class GitHubProvider(

    override val config: AiProviderConfig,

    private val apiClient: AiApiClient,

    private val modelRegistry: GitHubModelRegistry

) : AiProviderAdapter {

    override val provider: AiProvider =
        AiProvider.GITHUB

    override suspend fun getAvailableModels(): List<AiModel> {

        return modelRegistry.getModels()
    }

    override suspend fun analyze(

        request: AiAnalysisRequest

    ): AiAnalysisResult {

        return apiClient.execute(

            config,

            request

        )
    }

    override suspend fun isAvailable(): Boolean {

        return config.enabled
    }

    override suspend fun healthCheck(): Boolean {

        return config.enabled
    }

    override suspend fun validateConfiguration(): Boolean {

        return config.apiKey.isNotBlank() &&
                config.baseUrl.isNotBlank()
    }

    override suspend fun estimateCost(

        request: AiAnalysisRequest

    ): Double {

        return 0.0
    }

    override suspend fun estimateTokens(

        request: AiAnalysisRequest

    ): Int {

        return 0
    }
}