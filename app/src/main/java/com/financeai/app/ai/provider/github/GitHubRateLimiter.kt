package com.financeai.app.ai.provider.github

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * GitHub Models rate limiter.
 *
 * Prevents excessive API requests and
 * provides basic request throttling.
 *
 * Runtime values will be loaded from
 * Admin Panel configuration.
 */
class GitHubRateLimiter {

    private val mutex = Mutex()

    private var requestCount = 0

    private var windowStart = System.currentTimeMillis()

    /**
     * Returns true if request is allowed.
     */
    suspend fun tryAcquire(

        maxRequests: Int,

        windowMillis: Long

    ): Boolean = mutex.withLock {

        val now = System.currentTimeMillis()

        if (now - windowStart >= windowMillis) {

            requestCount = 0

            windowStart = now
        }

        if (requestCount >= maxRequests) {

            return false
        }

        requestCount++

        true
    }

    /**
     * Returns remaining requests.
     */
    suspend fun remainingRequests(

        maxRequests: Int,

        windowMillis: Long

    ): Int = mutex.withLock {

        val now = System.currentTimeMillis()

        if (now - windowStart >= windowMillis) {

            requestCount = 0

            windowStart = now
        }

        return maxRequests - requestCount
    }

    /**
     * Current request count.
     */
    suspend fun requestCount(): Int = mutex.withLock {

        requestCount
    }

    /**
     * Reset limiter.
     */
    suspend fun reset() = mutex.withLock {

        requestCount = 0

        windowStart = System.currentTimeMillis()
    }
}