package com.financeai.app.sms.parser

import com.financeai.app.sms.model.ParsedSms
import com.financeai.app.sms.model.TransactionType
import com.financeai.app.sms.util.SmsRegex
import java.time.Instant

/**
 * Parses UPI transaction SMS.
 *
 * Supports messages from:
 * - PhonePe
 * - Google Pay
 * - Paytm
 * - BHIM
 * - Bank UPI notifications
 */
class UpiSmsParser {

    fun parse(
        sender: String,
        message: String
    ): SmsParseResult {

        return try {

            val amount = extractAmount(message)

            val reference = extractReference(message)

            val transactionType = detectTransactionType(message)

            val account = extractAccount(message)

            val balance = extractBalance(message)

            SmsParseResult.Success(
                ParsedSms(
                    sender = sender,
                    message = message,
                    amount = amount,
                    merchant = extractMerchant(message),
                    account = account,
                    transactionType = transactionType,
                    transactionTime = Instant.now(),
                    referenceNumber = reference,
                    balance = balance,
                    currency = "INR",
                    confidence = 0.90f,
                    parserName = "UpiSmsParser",
                    rawSms = message
                )
            )

        } catch (e: Exception) {

            SmsParseResult.Failed(
                reason = e.message ?: "UPI parsing failed"
            )
        }
    }

    private fun extractAmount(message: String): Double? {

        return SmsRegex.AMOUNT
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
            ?.replace(",", "")
            ?.toDoubleOrNull()
    }

    private fun extractReference(message: String): String? {

        return SmsRegex.REFERENCE
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
    }

    private fun extractAccount(message: String): String? {

        return SmsRegex.ACCOUNT
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
    }

    private fun extractBalance(message: String): Double? {

        return SmsRegex.BALANCE
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
            ?.replace(",", "")
            ?.toDoubleOrNull()
    }

    private fun detectTransactionType(
        message: String
    ): TransactionType {

        return when {

            SmsRegex.DEBIT.containsMatchIn(message) ->
                TransactionType.DEBIT

            SmsRegex.CREDIT.containsMatchIn(message) ->
                TransactionType.CREDIT

            else ->
                TransactionType.UNKNOWN
        }
    }

    /**
     * Placeholder.
     *
     * Future AI engine will identify
     * merchant, VPA and receiver.
     */
    private fun extractMerchant(
        message: String
    ): String? {

        return null
    }
}