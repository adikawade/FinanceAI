package com.financeai.app.ai.orchestrator

import com.financeai.app.ai.model.AiAnalysisResult
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Dispatches AI execution results to registered listeners.
 *
 * Used by:
 * - Dashboard
 * - Usage Tracker
 * - Notification System
 * - Analytics
 * - Cache Layer
 *
 * This class never modifies the AI response.
 * It only broadcasts it.
 */
class AiResponseDispatcher {

    private val mutex = Mutex()

    private val listeners =

        CopyOnWriteArrayList<AiResponseListener>()

    /**
     * Registers listener.
     */
    suspend fun register(

        listener: AiResponseListener

    ) {

        mutex.withLock {

            if (!listeners.contains(listener)) {

                listeners.add(listener)
            }
        }
    }

    /**
     * Unregisters listener.
     */
    suspend fun unregister(

        listener: AiResponseListener

    ) {

        mutex.withLock {

            listeners.remove(listener)
        }
    }

    /**
     * Dispatches successful response.
     */
    suspend fun dispatchSuccess(

        context: AiExecutionContext,

        result: AiAnalysisResult

    ) {

        listeners.forEach {

            if (it.isEnabled()) {

                it.onSuccess(

                    context,

                    result
                )
            }
        }
    }

    /**
     * Dispatches failed execution.
     */
    suspend fun dispatchFailure(

        context: AiExecutionContext,

        exception: Throwable

    ) {

        listeners.forEach {

            if (it.isEnabled()) {

                it.onFailure(

                    context,

                    exception
                )
            }
        }
    }

    /**
     * Dispatches cancellation.
     */
    suspend fun dispatchCancelled(

        context: AiExecutionContext

    ) {

        listeners.forEach {

            if (it.isEnabled()) {

                it.onCancelled(

                    context
                )
            }
        }
    }

    /**
     * Clears all listeners.
     */
    suspend fun clear() {

        mutex.withLock {

            listeners.clear()
        }
    }

    /**
     * Returns listener count.
     */
    fun listenerCount(): Int {

        return listeners.size
    }

    /**
     * Returns true if dispatcher
     * has listeners.
     */
    fun hasListeners(): Boolean {

        return listeners.isNotEmpty()
    }
}

/**
 * Listener for AI execution events.
 */
interface AiResponseListener {

    /**
     * Whether listener is active.
     */
    fun isEnabled(): Boolean = true

    /**
     * Successful execution.
     */
    suspend fun onSuccess(

        context: AiExecutionContext,

        result: AiAnalysisResult

    )

    /**
     * Failed execution.
     */
    suspend fun onFailure(

        context: AiExecutionContext,

        exception: Throwable

    )

    /**
     * Cancelled execution.
     */
    suspend fun onCancelled(

        context: AiExecutionContext

    )
}