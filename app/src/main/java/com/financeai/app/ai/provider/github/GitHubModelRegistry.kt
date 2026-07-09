package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.AiModel

/**
 * Registry of GitHub Models supported
 * by FinanceAI.
 *
 * Models are loaded dynamically from
 * Admin Panel configuration.
 *
 * This registry only provides local
 * model definitions.
 */
class GitHubModelRegistry {

    /**
     * Returns all available GitHub models.
     */
    fun getModels(): List<AiModel> {

        return listOf(

            AiModel(
                id = "gpt-5",
                displayName = "GPT-5",
                providerModelId = "gpt-5"
            ),

            AiModel(
                id = "gpt-5-mini",
                displayName = "GPT-5 Mini",
                providerModelId = "gpt-5-mini"
            ),

            AiModel(
                id = "gpt-4.1",
                displayName = "GPT-4.1",
                providerModelId = "gpt-4.1"
            ),

            AiModel(
                id = "gpt-4.1-mini",
                displayName = "GPT-4.1 Mini",
                providerModelId = "gpt-4.1-mini"
            ),

            AiModel(
                id = "deepseek-v3",
                displayName = "DeepSeek V3",
                providerModelId = "deepseek-v3"
            ),

            AiModel(
                id = "llama-4",
                displayName = "Llama 4",
                providerModelId = "llama-4"
            )
        )
    }

    /**
     * Returns model by id.
     */
    fun getModel(

        modelId: String

    ): AiModel? {

        return getModels().firstOrNull {

            it.id.equals(

                modelId,

                ignoreCase = true

            )

        }
    }

    /**
     * Returns true if model exists.
     */
    fun hasModel(

        modelId: String

    ): Boolean {

        return getModel(modelId) != null
    }
}