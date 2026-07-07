package com.financeai.app.sms.usecase

import com.financeai.app.sms.model.ParsedSms
import com.financeai.app.sms.repository.SmsRepository

/**
 * Main use case responsible for processing
 * incoming financial SMS.
 *
 * Current Responsibilities:
 * - Validate SMS
 * - Parse SMS
 *
 * Future Responsibilities:
 * - AI Categorization
 * - Duplicate Detection
 * - Fraud Detection
 * - Transaction Creation
 * - Merchant Learning
 * - Cloud Sync
 */
class ProcessIncomingSmsUseCase(
    private val repository: SmsRepository
) {

    suspend operator fun invoke(
        sender: String,
        message: String
    ): ParsedSms? {

        if (sender.isBlank()) return null

        if (message.isBlank()) return null

        return repository.processIncomingSms(
            sender = sender,
            message = message
        )
    }
}