package com.financeai.app.sms.filter

/**
 * Filters incoming SMS before sending it to the parser.
 *
 * Goal:
 * - Reject obvious promotional messages.
 * - Accept likely financial messages.
 */
object SmsFilter {

    /**
     * Returns true if the SMS is likely a financial transaction.
     */
    fun isFinancialSms(
        sender: String,
        message: String
    ): Boolean {

        val normalizedSender = sender.uppercase()
        val normalizedMessage = message.lowercase()

        // Ignore promotional SMS
        if (SmsKeywords.PROMOTIONAL_KEYWORDS.any {
                normalizedMessage.contains(it.lowercase())
            }) {
            return false
        }

        // Bank sender check
        if (SmsKeywords.BANK_SENDERS.any {
                normalizedSender.contains(it.uppercase())
            }) {
            return true
        }

        // Financial keyword check
        if (SmsKeywords.FINANCIAL_KEYWORDS.any {
                normalizedMessage.contains(it.lowercase())
            }) {
            return true
        }

        return false
    }
}