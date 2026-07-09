package com.financeai.app.ai.usage

/**
 * Defines how daily AI credits are reset.
 *
 * Controlled completely by Admin Panel.
 */
data class AiCreditResetPolicy(

    /**
     * Enable reset policy.
     */
    val enabled: Boolean = true,

    /**
     * Reset strategy.
     */
    val resetType: CreditResetType = CreditResetType.DAILY,

    /**
     * Reset hour (0-23)
     */
    val resetHour: Int = 0,

    /**
     * Reset minute.
     */
    val resetMinute: Int = 0,

    /**
     * Use user's timezone.
     */
    val useUserTimezone: Boolean = true,

    /**
     * Carry forward unused credits.
     */
    val carryForwardEnabled: Boolean = false,

    /**
     * Maximum credits that can be carried.
     */
    val maxCarryForwardCredits: Long = 0,

    /**
     * Maximum daily credits.
     */
    val dailyCreditLimit: Long = 0
)

/**
 * Credit reset strategy.
 */
enum class CreditResetType {

    /**
     * Reset every calendar day.
     */
    DAILY,

    /**
     * Reset 24 hours after first usage.
     */
    EVERY_24_HOURS,

    /**
     * Reset weekly.
     */
    WEEKLY,

    /**
     * Reset monthly.
     */
    MONTHLY
}