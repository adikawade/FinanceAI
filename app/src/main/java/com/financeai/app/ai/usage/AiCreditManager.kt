package com.financeai.app.ai.usage

/**
 * Handles all AI credit operations.
 *
 * No UI or feature should directly modify credits.
 * Everything must go through this manager.
 */
class AiCreditManager(

    private val usageRepository: AiUsageRepository

) {

    /**
     * Returns true if user has enough credits.
     */
    suspend fun hasCredits(

        profile: AiUserProfile,

        requiredCredits: Long

    ): Boolean {

        if (profile.lifetimePrime) {

            return true
        }

        return totalCredits(profile) >= requiredCredits
    }

    /**
     * Returns total available credits.
     */
    fun totalCredits(

        profile: AiUserProfile

    ): Long {

        return profile.remainingDailyCredits +
                profile.creditBalance +
                profile.couponCredits +
                profile.referralCredits +
                profile.promotionalCredits
    }

    /**
     * Calculates remaining credits.
     */
    fun remainingCredits(

        profile: AiUserProfile

    ): Long {

        return totalCredits(profile)
    }

    /**
     * Returns required credits for feature.
     */
    fun requiredCredits(

        access: AiFeatureAccess

    ): Int {

        return access.creditCost
    }

    /**
     * Returns true if feature is free.
     */
    fun isFree(

        access: AiFeatureAccess

    ): Boolean {

        return access.creditCost <= 0
    }

    /**
     * Validates if deduction is allowed.
     */
    suspend fun canDeduct(

        profile: AiUserProfile,

        access: AiFeatureAccess

    ): Boolean {

        if (isFree(access)) {

            return true
        }

        return hasCredits(

            profile,

            access.creditCost.toLong()

        )
    }
}