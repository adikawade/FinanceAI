package com.financeai.app.sms.parser

import com.financeai.app.sms.model.ParsedSms
import com.financeai.app.sms.model.TransactionType
import com.financeai.app.sms.util.SmsRegex
import java.time.Instant

/**
 * Parses bank transaction SMS.
 *
 * This parser extracts transaction details from
 * bank debit/credit SMS messages.
 */
class BankSmsParser {

    fun parse(
        sender: String,
        message: String
    ): SmsParseResult {

        return try {

            val amount = extractAmount(message)

            val account = extractAccount(message)

            val balance = extractBalance(message)

            val reference = extractReference(message)

            val transactionType = detectTransactionType(message)

            val merchant = extractMerchant(message)

            SmsParseResult.Success(
                ParsedSms(
                    sender = sender,
                    message = message,
                    amount = amount,
                    merchant = merchant,
                    account = account,
                    transactionType = transactionType,
                    transactionTime = Instant.now(),
                    referenceNumber = reference,
                    balance = balance,
                    currency = "INR",
                    confidence = 0.80f,
                    parserName = "BankSmsParser",
                    rawSms = message
                )
            )

        } catch (e: Exception) {

            SmsParseResult.Failed(
                reason = e.message ?: "Unknown parsing error"
            )
        }
    }

    private fun extractAmount(message: String): Double? {

        val match = SmsRegex.AMOUNT.find(message)
            ?: return null

        return match.groupValues[1]
            .replace(",", "")
            .toDoubleOrNull()
    }

    private fun extractAccount(message: String): String? {

        return SmsRegex.ACCOUNT
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
    }

    private fun extractBalance(message: String): Double? {

        val match = SmsRegex.BALANCE.find(message)
            ?: return null

        return match.groupValues[1]
            .replace(",", "")
            .toDoubleOrNull()
    }

    private fun extractReference(message: String): String? {

        return SmsRegex.REFERENCE
            .find(message)
            ?.groupValues
            ?.getOrNull(1)
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
     * AI Merchant Engine will replace this later.
     */
    private fun extractMerchant(
        message: String
    ): String? {

        return null
    }
}