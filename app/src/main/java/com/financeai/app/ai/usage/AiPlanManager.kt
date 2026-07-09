package com.financeai.app.ai.usage

import java.time.Instant

/**
 * Handles AI plan lifecycle.
 *
 * Everything related to AI plans
 * must go through this manager.
 *
 * No UI or business logic should
 * directly modify plan information.
 */
class AiPlanManager {

    /**
     * Returns true if user has Prime.
     */
    fun isPrime(

        profile: AiUserProfile

    ): Boolean {

        if (profile.lifetimePrime) {

            return true
        }

        val expiry = profile.primeExpiry
            ?: return false

        return expiry.isAfter(Instant.now())
    }

    /**
     * Returns true if Day Pass is active.
     */
    fun isDayPassActive(

        profile: AiUserProfile

    ): Boolean {

        val expiry = profile.dayPassExpiry
            ?: return false

        return expiry.isAfter(Instant.now())
    }

    /**
     * Returns current plan.
     */
    fun currentPlan(

        profile: AiUserProfile

    ): String {

        return when {

            isPrime(profile) -> "PRIME"

            isDayPassActive(profile) -> "DAY_PASS"

            else -> "FREE"
        }
    }

    /**
     * Returns true if user can access
     * premium AI features.
     */
    fun hasPremiumAccess(

        profile: AiUserProfile

    ): Boolean {

        return isPrime(profile) ||
                isDayPassActive(profile)
    }

    /**
     * Returns true if user is lifetime member.
     */
    fun isLifetimeMember(

        profile: AiUserProfile

    ): Boolean {

        return profile.lifetimePrime
    }

    /**
     * Checks whether AI is blocked.
     */
    fun isBlocked(

        profile: AiUserProfile

    ): Boolean {

        return profile.aiBlocked
    }
}