package com.financeai.app.sms.usecase

import com.financeai.app.sms.model.ParsedSms
import com.financeai.app.sms.repository.SmsRepository

/**
 * Parses an SMS without saving it.
 *
 * Responsibility:
 * - Validate input
 * - Delegate parsing to repository
 * - Return parsed result
 */
class ParseSmsUseCase(
    private val repository: SmsRepository
) {

    suspend operator fun invoke(
        sender: String,
        message: String
    ): ParsedSms? {

        if (sender.isBlank()) return null

        if (message.isBlank()) return null

        return repository.parseSms(
            sender = sender,
            message = message
        )
    }
}