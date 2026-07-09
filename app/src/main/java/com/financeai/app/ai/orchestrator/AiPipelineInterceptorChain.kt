package com.financeai.app.ai.orchestrator

/**
 * Executes all registered pipeline interceptors
 * in priority order.
 *
 * Every pipeline stage passes through this chain.
 */
class AiPipelineInterceptorChain(

    interceptors: List<AiPipelineInterceptor>

) {

    /**
     * Sorted interceptors.
     *
     * Higher priority executes first.
     */
    private val interceptors =

        interceptors

            .filter {

                it.isEnabled()

            }

            .sortedByDescending {

                it.priority

            }

    /**
     * Executes before-stage callbacks.
     */
    suspend fun beforeStage(

        context: AiExecutionContext,

        stage: AiPipelineStage

    ) {

        interceptors

            .filter {

                it.supports(stage)

            }

            .forEach {

                it.beforeStage(

                    context,

                    stage

                )

            }
    }

    /**
     * Executes after-stage callbacks.
     */
    suspend fun afterStage(

        context: AiExecutionContext,

        stage: AiPipelineStage,

        result: AiExecutionResult?

    ) {

        interceptors

            .filter {

                it.supports(stage)

            }

            .forEach {

                it.afterStage(

                    context,

                    stage,

                    result

                )

            }
    }

    /**
     * Executes error callbacks.
     */
    suspend fun onError(

        context: AiExecutionContext,

        stage: AiPipelineStage,

        exception: Throwable

    ) {

        interceptors

            .filter {

                it.supports(stage)

            }

            .forEach {

                it.onError(

                    context,

                    stage,

                    exception

                )

            }
    }

    /**
     * Executes completion callbacks.
     */
    suspend fun onCompleted(

        context: AiExecutionContext,

        result: AiExecutionResult

    ) {

        interceptors.forEach {

            it.onCompleted(

                context,

                result

            )

        }
    }

    /**
     * Executes cancellation callbacks.
     */
    suspend fun onCancelled(

        context: AiExecutionContext

    ) {

        interceptors.forEach {

            it.onCancelled(

                context

            )

        }
    }

    /**
     * Number of active interceptors.
     */
    fun size(): Int {

        return interceptors.size
    }

    /**
     * Returns true if chain is empty.
     */
    fun isEmpty(): Boolean {

        return interceptors.isEmpty()
    }

    /**
     * Returns immutable interceptor list.
     */
    fun all(): List<AiPipelineInterceptor> {

        return interceptors.toList()
    }
}