package com.financeai.app.ai.orchestrator

/**
 * Defines execution priority of an AI request.
 *
 * Priority is used by:
 * - Request Queue
 * - Scheduler
 * - Background Workers
 * - Admin Rules
 */
enum class AiRequestPriority(

    /**
     * Numeric priority.
     *
     * Higher value = Higher priority.
     */
    val level: Int,

    /**
     * Human readable label.
     */
    val displayName: String

) {

    /**
     * Emergency requests.
     */
    CRITICAL(

        level = 500,

        displayName = "Critical"

    ),

    /**
     * Highest priority.
     */
    HIGH(

        level = 400,

        displayName = "High"

    ),

    /**
     * Default priority.
     */
    NORMAL(

        level = 300,

        displayName = "Normal"

    ),

    /**
     * Background work.
     */
    LOW(

        level = 200,

        displayName = "Low"

    ),

    /**
     * Non-urgent maintenance work.
     */
    BACKGROUND(

        level = 100,

        displayName = "Background"

    );

    /**
     * Returns true if this priority
     * is higher than another.
     */
    fun higherThan(

        other: AiRequestPriority

    ): Boolean {

        return level > other.level
    }

    /**
     * Returns true if this priority
     * is lower than another.
     */
    fun lowerThan(

        other: AiRequestPriority

    ): Boolean {

        return level < other.level
    }

    companion object {

        /**
         * Returns priority from integer.
         */
        fun fromLevel(

            level: Int

        ): AiRequestPriority {

            return entries

                .sortedByDescending {

                    it.level

                }

                .firstOrNull {

                    level >= it.level

                }

                ?: NORMAL
        }
    }
}