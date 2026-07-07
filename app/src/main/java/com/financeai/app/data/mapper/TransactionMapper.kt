package com.financeai.app.data.mapper

import com.financeai.app.data.local.entity.TransactionEntity
import com.financeai.app.domain.model.Category
import com.financeai.app.domain.model.Transaction
import com.financeai.app.domain.model.TransactionType
import java.time.Instant

fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        id = id,
        amount = amount,
        merchant = merchant,
        category = runCatching { Category.valueOf(category) }.getOrDefault(Category.OTHER),
        type = runCatching { TransactionType.valueOf(type) }.getOrDefault(TransactionType.EXPENSE),
        timestamp = Instant.ofEpochMilli(timestamp),
        accountName = accountName,
        note = note,
        verified = verified,
        aiCategorized = aiCategorized,
        aiConfidence = aiConfidence
    )
}

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        amount = amount,
        merchant = merchant,
        category = category.name,
        type = type.name,
        timestamp = timestamp.toEpochMilli(),
        accountName = accountName,
        note = note,
        verified = verified,
        aiCategorized = aiCategorized,
        aiConfidence = aiConfidence
    )
}
