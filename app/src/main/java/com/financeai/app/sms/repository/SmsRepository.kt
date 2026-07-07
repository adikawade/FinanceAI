package com.financeai.app.sms.repository

import com.financeai.app.sms.model.ParsedSms
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for SMS processing.
 *
 * This interface isolates the SMS engine from
 * the rest of the application.
 */
interface SmsRepository {

    /**
     * Process a newly received SMS.
     *
     * Returns the parsed result if the SMS
     * represents a financial transaction.
     */
    suspend fun processIncomingSms(
        sender: String,
        message: String
    ): ParsedSms?

    /**
     * Parse an SMS without storing it.
     */
    suspend fun parseSms(
        sender: String,
        message: String
    ): ParsedSms?

    /**
     * Observe parsed SMS stream.
     *
     * Future:
     * - AI
     * - Verification
     * - Background Sync
     */
    fun observeParsedSms(): Flow<List<ParsedSms>>

    /**
     * Clear temporary SMS cache.
     */
    suspend fun clearCache()
}