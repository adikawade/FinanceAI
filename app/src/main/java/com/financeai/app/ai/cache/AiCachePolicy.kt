package com.financeai.app.ai.cache

/**
 * Cache policy for each AI feature.
 */
data class AiCachePolicy(

    val enabled: Boolean = true,

    val keepAfterModelChange: Boolean = true,

    val refreshInBackground: Boolean = true,

    val ttlMinutes: Long,

    val maxEntries: Int = 1000
)