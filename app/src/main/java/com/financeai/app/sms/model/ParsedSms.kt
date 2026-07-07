package com.financeai.app.sms.model

import java.time.Instant

/**
 * Represents a parsed financial SMS before it is converted
 * into a Transaction.
 */
data class ParsedSms(

    val sender: String,

    val message: String,

    val amount: Double?,

    val merchant: String?,

    val account: String?,

    val transactionType: TransactionType,

    val transactionTime: Instant,

    val referenceNumber: String?,

    val balance: Double?,

    val currency: String = "INR",

    val confidence: Float = 0f,

    val parserName: String = "",

    val rawSms: String
)

enum class TransactionType {
    CREDIT,
    DEBIT,
    UNKNOWN
}