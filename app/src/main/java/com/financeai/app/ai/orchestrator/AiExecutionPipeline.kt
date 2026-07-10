package com.financeai.app.ai.orchestrator

/**
 * Executes the complete AI pipeline.
 *
 * Responsibilities:
 * - Execute pipeline stages
 * - Invoke interceptors
 * - Measure execution time
 * - Collect metrics
 * - Delegate request execution
 */
class AiExecutionPipeline(

    private val configuration: AiPipelineConfiguration,

    private val interceptorChain: AiPipelineInterceptorChain,

    private val logger: AiPipelineLogger,

    private val timer: AiExecutionTimer,

    private val metricsCollector: AiMetricsCollector,

    private val requestExecutor: AiRequestExecutor

) {

    /**
     * Executes a complete AI pipeline.
     */
    suspend fun execute(

        context: AiExecutionContext

    ): AiExecutionResult {

        timer.startStage(

            AiPipelineStage.PROVIDER_EXECUTION

        )

        try {

            beforeStage(

                context,

                AiPipelineStage.PROVIDER_EXECUTION

            )

            val result =

                requestExecutor.execute(

                    context

                )

            afterStage(

                context,

                AiPipelineStage.PROVIDER_EXECUTION,

                result

            )

            timer.finishStage(

                AiPipelineStage.PROVIDER_EXECUTION

            )

            timer.finishExecution()

            metricsCollector.recordSuccess(

                duration =

                    timer.executionDuration(),

                cacheHit = result.cacheHit

            )

            interceptorChain.onCompleted(

                context,

                result

            )

            return result

        } catch (

            exception: Throwable

        ) {

            timer.finishExecution()

            metricsCollector.recordFailure(

                timer.executionDuration()

            )

            interceptorChain.onError(

                context,

                AiPipelineStage.PROVIDER_EXECUTION,

                exception

            )

            logger.error(

                executionId =

                    context.executionId,

                stage =

                    AiPipelineStage.PROVIDER_EXECUTION,

                message =

                    exception.message ?: "Unknown error",

                throwable = exception

            )

            throw exception
        }
    }

    /**
     * Executes before-stage lifecycle.
     */
    private suspend fun beforeStage(

        context: AiExecutionContext,

        stage: AiPipelineStage

    ) {

        interceptorChain.beforeStage(

            context,

            stage

        )

        if (

            configuration.shouldLog()

        ) {

            logger.info(

                executionId =

                    context.executionId,

                stage = stage,

                message =

                    "Pipeline stage started."

            )
        }
    }

    /**
     * Executes after-stage lifecycle.
     */
    private suspend fun afterStage(

        context: AiExecutionContext,

        stage: AiPipelineStage,

        result: AiExecutionResult

    ) {

        interceptorChain.afterStage(

            context,

            stage,

            result

        )

        if (

            configuration.shouldLog()

        ) {

            logger.info(

                executionId =

                    context.executionId,

                stage = stage,

                message =

                    "Pipeline stage completed."

            )
        }
    }
}