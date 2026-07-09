package com.financeai.app.ai.usage

import com.financeai.app.ai.model.AiModel
import com.financeai.app.ai.model.AiProvider
import java.time.Instant

/**
 * AI profile of a user.
 *
 * This model contains all AI related
 * user configuration.
 *
 * Everything here can be overridden
 * by the Admin Panel.
 */
data class AiUserProfile(

    /**
     * User id.
     */
    val userId: String,

    /**
     * Current AI Plan.
     *
     * FREE
     * PRIME
     * DAY_PASS
     */
    val planId: String = "FREE",

    /**
     * Current AI Credit Balance.
     */
    val creditBalance: Long = 0,

    /**
     * Total purchased credits.
     */
    val lifetimePurchasedCredits: Long = 0,

    /**
     * Total consumed credits.
     */
    val lifetimeUsedCredits: Long = 0,

    /**
     * Daily free credits.
     */
    val dailyCredits: Long = 0,

    /**
     * Remaining daily credits.
     */
    val remainingDailyCredits: Long = 0,

    /**
     * Prime expiry.
     */
    val primeExpiry: Instant? = null,

    /**
     * Day pass expiry.
     */
    val dayPassExpiry: Instant? = null,

    /**
     * Lifetime prime.
     */
    val lifetimePrime: Boolean = false,

    /**
     * Admin override enabled.
     */
    val adminOverride: Boolean = false,

    /**
     * Force AI Provider.
     */
    val forcedProvider: AiProvider? = null,

    /**
     * Force AI Model.
     */
    val forcedModel: AiModel? = null,

    /**
     * Custom daily AI chat limit.
     */
    val customChatLimit: Int? = null,

    /**
     * Custom SMS AI limit.
     */
    val customSmsLimit: Int? = null,

    /**
     * Custom AI credit limit.
     */
    val customCreditLimit: Long? = null,

    /**
     * Coupon credits.
     */
    val couponCredits: Long = 0,

    /**
     * Referral credits.
     */
    val referralCredits: Long = 0,

    /**
     * Promotional credits.
     */
    val promotionalCredits: Long = 0,

    /**
     * User blocked from AI.
     */
    val aiBlocked: Boolean = false,

    /**
     * AI block reason.
     */
    val blockReason: String? = null,

    /**
     * Last updated.
     */
    val updatedAt: Instant = Instant.now()
)