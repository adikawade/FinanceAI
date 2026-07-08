package com.financeai.app.ai.cache

import java.security.MessageDigest

object AiCacheKeyGenerator {

    fun generate(

        userId: String,

        feature: String,

        input: String,

        language: String,

        version: Int = 1

    ): String {

        val hash = sha256(input)

        return listOf(

            userId,

            feature,

            language,

            version,

            hash

        ).joinToString("_")
    }

    private fun sha256(

        value: String

    ): String {

        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(value.toByteArray())

        return bytes.joinToString("") {

            "%02x".format(it)

        }
    }
}