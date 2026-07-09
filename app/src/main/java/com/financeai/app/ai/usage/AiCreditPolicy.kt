package com.financeai.app.ai.usage

/**
 * Defines how AI credits should be deducted.
 *
 * Entire policy is controlled from
 * the Admin Panel.
 */
data class AiCreditPolicy(

    /**
     * Policy enabled.
     */
    val enabled: Boolean = true,

    /**
     * Credit deduction priority.
     */
    val deductionOrder: List<CreditSource> = listOf(

        CreditSource.DAILY,

        CreditSource.PROMOTIONAL,

        CreditSource.COUPON,

        CreditSource.REFERRAL,

        CreditSource.PURCHASED
    ),

    /**
     * Allow daily credits.
     */
    val allowDailyCredits: Boolean = true,

    /**
     * Allow purchased credits.
     */
    val allowPurchasedCredits: Boolean = true,

    /**
     * Allow coupon credits.
     */
    val allowCouponCredits: Boolean = true,

    /**
     * Allow referral credits.
     */
    val allowReferralCredits: Boolean = true,

    /**
     * Allow promotional credits.
     */
    val allowPromotionalCredits: Boolean = true,

    /**
     * Allow negative balance.
     */
    val allowNegativeBalance: Boolean = false,

    /**
     * Maximum negative credits.
     */
    val maxNegativeCredits: Long = 0
)

/**
 * Credit source.
 */
enum class CreditSource {

    DAILY,

    PURCHASED,

    COUPON,

    REFERRAL,

    PROMOTIONAL
}