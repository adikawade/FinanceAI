package com.financeai.app.ai.provider.github

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiProviderConfig
import com.financeai.app.ai.network.AiRequestMapper

/**
 * Maps FinanceAI AI requests
 * into GitHub Models API requests.
 */
class GitHubRequestMapper : AiRequestMapper {

    private val json = Json {

        ignoreUnknownKeys = true

        encodeDefaults = true

        prettyPrint = false

    }

    override fun mapRequest(

        config: AiProviderConfig,

        request: AiAnalysisRequest

    ): String {

        val payload = GitHubChatRequest(

            model = config.defaultModel,

            messages = listOf(

                GitHubMessage(

                    role = "system",

                    content = request.systemPrompt

                ),

                GitHubMessage(

                    role = "user",

                    content = request.prompt

                )

            ),

            temperature = request.temperature,

            maxTokens = request.maxOutputTokens

        )

        return json.encodeToString(payload)
    }

    override fun mapHeaders(

        config: AiProviderConfig

    ): Map<String, String> {

        return mapOf(

            "Authorization" to "Bearer ${config.apiKey}",

            "Content-Type" to "application/json"

        )
    }

    override fun endpoint(

        config: AiProviderConfig

    ): String {

        return "${config.baseUrl}/chat/completions"
    }
}

/**
 * GitHub Chat Request
 */
@Serializable
data class GitHubChatRequest(

    val model: String,

    val messages: List<GitHubMessage>,

    val temperature: Float,

    val maxTokens: Int
)

/**
 * GitHub Chat Message
 */
@Serializable
data class GitHubMessage(

    val role: String,

    val content: String
)