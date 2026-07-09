package com.financeai.app.ai.usage

/**
 * Central access controller for every AI feature.
 *
 * Every AI request must be validated here before
 * reaching any AI provider.
 */
class AiAccessManager(

    private val creditManager: AiCreditManager,

    private val planManager: AiPlanManager

) {

    /**
     * Returns access result.
     */
    suspend fun canAccess(

        profile: AiUserProfile,

        feature: AiFeatureAccess

    ): AiAccessResult {

        // User blocked
        if (planManager.isBlocked(profile)) {

            return AiAccessResult(

                allowed = false,

                reason = "AI access blocked."
            )
        }

        // Feature disabled
        if (!feature.enabled) {

            return AiAccessResult(

                allowed = false,

                reason = "Feature disabled."
            )
        }

        // Credit validation
        if (!creditManager.canDeduct(profile, feature)) {

            return AiAccessResult(

                allowed = false,

                reason = "Insufficient AI Credits."
            )
        }

        // Premium feature
        if (feature.priority > 0 &&
            !planManager.hasPremiumAccess(profile)
        ) {

            return AiAccessResult(

                allowed = false,

                reason = "Premium plan required."
            )
        }

        return AiAccessResult(

            allowed = true,

            reason = null
        )
    }
}