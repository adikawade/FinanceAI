package com.financeai.app.sms.parser

import com.financeai.app.sms.filter.SmsFilter

/**
 * Central SMS parser.
 *
 * Responsibilities:
 * 1. Filter non-financial SMS.
 * 2. Detect SMS type.
 * 3. Delegate parsing to the correct parser.
 */
class SmsParser(
    private val bankSmsParser: BankSmsParser = BankSmsParser(),
    private val upiSmsParser: UpiSmsParser = UpiSmsParser()
) {

    fun parse(
        sender: String,
        message: String
    ): SmsParseResult {

        // Ignore non-financial SMS
        if (!SmsFilter.isFinancialSms(sender, message)) {
            return SmsParseResult.Ignored
        }

        return when {

            isUpiMessage(message) ->
                upiSmsParser.parse(sender, message)

            else ->
                bankSmsParser.parse(sender, message)
        }
    }

    /**
     * Determines whether the SMS is UPI related.
     */
    private fun isUpiMessage(message: String): Boolean {

        val text = message.lowercase()

        return text.contains("upi") ||
                text.contains("vpa") ||
                text.contains("collect request") ||
                text.contains("phonepe") ||
                text.contains("gpay") ||
                text.contains("google pay") ||
                text.contains("paytm") ||
                text.contains("bhim")
    }
}