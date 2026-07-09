package com.financeai.app.ai.usage

import com.financeai.app.ai.model.AiFeature
import com.financeai.app.ai.model.AiModel
import com.financeai.app.ai.model.AiProvider

/**
 * Controls access to one AI feature.
 *
 * Everything here is configurable
 * from the Admin Panel.
 */
data class AiFeatureAccess(

    /**
     * Feature identifier.
     */
    val feature: AiFeature,

    /**
     * Feature enabled.
     */
    val enabled: Boolean = true,

    /**
     * Credit cost.
     */
    val creditCost: Int = 0,

    /**
     * Daily limit.
     *
     * -1 = Unlimited
     */
    val dailyLimit: Int = -1,

    /**
     * Monthly limit.
     *
     * -1 = Unlimited
     */
    val monthlyLimit: Int = -1,

    /**
     * Provider.
     */
    val provider: AiProvider,

    /**
     * Model.
     */
    val model: AiModel,

    /**
     * Allow cache.
     */
    val cacheEnabled: Boolean = true,

    /**
     * Allow background refresh.
     */
    val backgroundRefresh: Boolean = true,

    /**
     * Priority.
     */
    val priority: Int = 0
)