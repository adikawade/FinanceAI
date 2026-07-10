package com.financeai.app.ai.chat

import com.financeai.app.ai.model.AiAnalysisRequest

/**
 * Builds the final prompt context that will be
 * sent to the AI provider.
 *
 * Responsibilities:
 * - Include conversation history
 * - Inject system instructions
 * - Respect context window
 * - Prepare provider-independent prompt
 */
class AiContextBuilder(

    private val maxContextMessages: Int = 20

) {

    /**
     * Creates provider-ready context.
     */
    fun build(

        conversation: AiConversation?,

        request: AiAnalysisRequest,

        systemPrompt: String? = null

    ): AiContext {

        val messages = mutableListOf<AiContextMessage>()

        if (!systemPrompt.isNullOrBlank()) {

            messages.add(

                AiContextMessage(

                    role = AiChatRole.SYSTEM,

                    content = systemPrompt
                )
            )
        }

        conversation
            ?.messages
            ?.takeLast(maxContextMessages)
            ?.forEach {

                messages.add(

                    AiContextMessage(

                        role = it.role,

                        content = it.content
                    )
                )
            }

        messages.add(

            AiContextMessage(

                role = AiChatRole.USER,

                content = request.input
            )
        )

        return AiContext(

            feature = request.feature,

            messages = messages,

            metadata = request.context,

            options = request.options
        )
    }
}

/**
 * Final provider-independent context.
 */
data class AiContext(

    val feature: com.financeai.app.ai.model.AiFeature,

    val messages: List<AiContextMessage>,

    val metadata: Map<String, String>,

    val options: Map<String, String>
)

/**
 * One message inside AI context.
 */
data class AiContextMessage(

    val role: AiChatRole,

    val content: String
)