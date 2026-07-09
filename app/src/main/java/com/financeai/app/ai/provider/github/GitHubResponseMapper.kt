package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.*
import com.financeai.app.ai.network.AiResponseMapper
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Maps GitHub Models API response
 * into FinanceAI response.
 */
class GitHubResponseMapper : AiResponseMapper {

    private val json = Json {

        ignoreUnknownKeys = true

        isLenient = true

    }

    override fun mapResponse(

        response: String

    ): AiAnalysisResult {

        val apiResponse = json.decodeFromString<GitHubChatResponse>(

            response

        )

        val content = apiResponse

            .choices

            .firstOrNull()

            ?.message

            ?.content

            ?: ""

        return AiAnalysisResult(

            success = true,

            predictions = emptyList(),

            confidence = AiConfidence(

                score = 1.0f,

                level = ConfidenceLevel.HIGH,

                requiresVerification = false,

                reason = "GitHub Models"

            ),

            summary = content,

            explanation = content,

            provider = "GitHub",

            model = apiResponse.model,

            metadata = mapOf(

                "promptTokens" to apiResponse.usage.promptTokens.toString(),

                "completionTokens" to apiResponse.usage.completionTokens.toString(),

                "totalTokens" to apiResponse.usage.totalTokens.toString()

            )

        )
    }
}

@Serializable
data class GitHubChatResponse(

    val id: String,

    val model: String,

    val choices: List<GitHubChoice>,

    val usage: GitHubUsage
)

@Serializable
data class GitHubChoice(

    val index: Int,

    val message: GitHubAssistantMessage,

    val finish_reason: String? = null
)

@Serializable
data class GitHubAssistantMessage(

    val role: String,

    val content: String
)

@Serializable
data class GitHubUsage(

    val promptTokens: Int = 0,

    val completionTokens: Int = 0,

    val totalTokens: Int = 0
)