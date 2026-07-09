package com.financeai.app.ai.provider.github

import com.financeai.app.ai.model.AiAnalysisRequest
import com.financeai.app.ai.model.AiAnalysisResult
import com.financeai.app.ai.model.AiProviderConfig
import com.financeai.app.ai.network.AiApiClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

/**
 * GitHub Models API Client.
 *
 * Responsible only for HTTP communication.
 */
class GitHubApiClient(

    private val requestMapper: GitHubRequestMapper,

    private val responseMapper: GitHubResponseMapper

) : AiApiClient {

    private val client = OkHttpClient.Builder()

        .connectTimeout(30, TimeUnit.SECONDS)

        .readTimeout(60, TimeUnit.SECONDS)

        .writeTimeout(60, TimeUnit.SECONDS)

        .retryOnConnectionFailure(true)

        .build()

    override suspend fun execute(

        config: AiProviderConfig,

        request: AiAnalysisRequest

    ): AiAnalysisResult {

        val jsonBody = requestMapper.mapRequest(

            config,

            request

        )

        val body = jsonBody.toRequestBody(

            "application/json".toMediaType()

        )

        val builder = Request.Builder()

            .url(

                requestMapper.endpoint(config)

            )

            .post(body)

        requestMapper

            .mapHeaders(config)

            .forEach {

                (key, value) ->

                builder.addHeader(

                    key,

                    value

                )

            }

        val response = client

            .newCall(

                builder.build()

            )

            .execute()

        if (!response.isSuccessful) {

            throw RuntimeException(

                "GitHub Models Error : ${response.code}"

            )
        }

        val responseBody =

            response.body?.string()

                ?: throw RuntimeException(

                    "Empty response."

                )

        return responseMapper

            .mapResponse(

                responseBody

            )
    }
}