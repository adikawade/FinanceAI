package com.financeai.app.sms.parser

import com.financeai.app.sms.model.ParsedSms

/**
 * Result returned after parsing an incoming SMS.
 */
sealed class SmsParseResult {

    /**
     * SMS successfully parsed.
     */
    data class Success(
        val parsedSms: ParsedSms
    ) : SmsParseResult()

    /**
     * SMS is not related to finance.
     */
    data object Ignored : SmsParseResult()

    /**
     * SMS appears financial but parsing failed.
     */
    data class Failed(
        val reason: String
    ) : SmsParseResult()
}