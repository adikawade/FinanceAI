package com.financeai.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey
    val id: String,

    val amount: Double,

    val merchant: String,

    val category: String,

    val type: String,

    val timestamp: Long,

    val accountName: String = "",

    val note: String = "",

    val verified: Boolean = false,

    val aiCategorized: Boolean = false,

    val aiConfidence: Float = 0f
)