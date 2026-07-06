package com.financeai.app.domain.model

import java.time.Instant

data class Transaction(
    val id: String,
    val amount: Double,

    val merchant: String,

    val category: Category,

    val type: TransactionType,

    val timestamp: Instant,

    val accountName: String = "",

    val note: String = "",

    val verified: Boolean = false,

    val aiCategorized: Boolean = false,

    val aiConfidence: Float = 0f
)

enum class TransactionType {
    INCOME,
    EXPENSE
}