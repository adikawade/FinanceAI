package com.financeai.app.ai.chat

import com.financeai.app.ai.model.AiAnalysisRequest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages AI conversations.
 *
 * Responsibilities:
 * - Create conversations
 * - Store message history
 * - Maintain context window
 * - Prune old messages
 * - Track conversation activity
 */
class AiConversationManager(

    private val maxMessagesPerConversation: Int = 50

) {

    private val mutex = Mutex()

    private val conversations =

        ConcurrentHashMap<String, AiConversation>()

    /**
     * Creates a new conversation.
     */
    suspend fun createConversation(

        userId: String,

        title: String? = null

    ): AiConversation {

        return mutex.withLock {

            val conversation = AiConversation(

                conversationId = UUID.randomUUID().toString(),

                userId = userId,

                title = title ?: "New Conversation",

                createdAt = Instant.now(),

                updatedAt = Instant.now()

            )

            conversations[conversation.conversationId] = conversation

            conversation
        }
    }

    /**
     * Returns conversation.
     */
    fun getConversation(

        conversationId: String

    ): AiConversation? {

        return conversations[conversationId]
    }

    /**
     * Adds a user request.
     */
    suspend fun addUserMessage(

        conversationId: String,

        request: AiAnalysisRequest

    ) {

        mutex.withLock {

            val conversation =

                conversations[conversationId]

                    ?: return

            val updatedMessages =

                conversation.messages.toMutableList()

            updatedMessages.add(

                AiChatMessage(

                    id = UUID.randomUUID().toString(),

                    role = AiChatRole.USER,

                    content = request.input,

                    timestamp = Instant.now()
                )
            )

            conversations[conversationId] =

                conversation.copy(

                    updatedAt = Instant.now(),

                    messages = prune(updatedMessages)
                )
        }
    }

    /**
     * Adds assistant response.
     */
    suspend fun addAssistantMessage(

        conversationId: String,

        message: String

    ) {

        mutex.withLock {

            val conversation =

                conversations[conversationId]

                    ?: return

            val updatedMessages =

                conversation.messages.toMutableList()

            updatedMessages.add(

                AiChatMessage(

                    id = UUID.randomUUID().toString(),

                    role = AiChatRole.ASSISTANT,

                    content = message,

                    timestamp = Instant.now()
                )
            )

            conversations[conversationId] =

                conversation.copy(

                    updatedAt = Instant.now(),

                    messages = prune(updatedMessages)
                )
        }
    }

    /**
     * Deletes conversation.
     */
    suspend fun deleteConversation(

        conversationId: String

    ) {

        mutex.withLock {

            conversations.remove(conversationId)
        }
    }

    /**
     * Returns all user conversations.
     */
    fun conversations(

        userId: String

    ): List<AiConversation> {

        return conversations.values

            .filter {

                it.userId == userId

            }

            .sortedByDescending {

                it.updatedAt

            }
    }

    /**
     * Clears all conversations.
     */
    suspend fun clear() {

        mutex.withLock {

            conversations.clear()
        }
    }

    /**
     * Keeps latest messages only.
     */
    private fun prune(

        messages: List<AiChatMessage>

    ): List<AiChatMessage> {

        if (

            messages.size <= maxMessagesPerConversation

        ) {

            return messages
        }

        return messages.takeLast(

            maxMessagesPerConversation
        )
    }
}

data class AiConversation(

    val conversationId: String,

    val userId: String,

    val title: String,

    val createdAt: Instant,

    val updatedAt: Instant,

    val messages: List<AiChatMessage> = emptyList()
)

data class AiChatMessage(

    val id: String,

    val role: AiChatRole,

    val content: String,

    val timestamp: Instant
)

enum class AiChatRole {

    USER,

    ASSISTANT,

    SYSTEM
}