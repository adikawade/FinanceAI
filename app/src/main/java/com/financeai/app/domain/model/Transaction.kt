package com.financeai.app.domain.model

import java.time.Instant

data class Transaction(
    val id: String,
    val amount: Double,
    val merchant: String,
    val category: String,
    val type: String,
    val timestamp: Instant,
    val verified: Boolean = false
)
