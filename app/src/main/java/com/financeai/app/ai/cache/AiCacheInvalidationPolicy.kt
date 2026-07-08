package com.financeai.app.ai.cache

enum class AiCacheInvalidationPolicy {

    /**
     * Never invalidate on model change.
     */
    KEEP,

    /**
     * Refresh in background.
     */
    BACKGROUND_REFRESH,

    /**
     * Clear immediately.
     */
    CLEAR
}