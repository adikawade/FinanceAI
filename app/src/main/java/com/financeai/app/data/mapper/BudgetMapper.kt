package com.financeai.app.data.mapper

import com.financeai.app.data.local.entity.BudgetEntity
import com.financeai.app.domain.model.Budget
import com.financeai.app.domain.model.Category
import java.time.Instant

fun BudgetEntity.toDomain(): Budget {
    return Budget(
        id = id,
        category = runCatching {
            Category.valueOf(category)
        }.getOrDefault(Category.OTHER),
        monthlyLimit = monthlyLimit,
        currentSpent = currentSpent,
        startDate = Instant.ofEpochMilli(startDate),
        endDate = Instant.ofEpochMilli(endDate),
        active = active
    )
}

fun Budget.toEntity(): BudgetEntity {
    return BudgetEntity(
        id = id,
        category = category.name,
        monthlyLimit = monthlyLimit,
        currentSpent = currentSpent,
        startDate = startDate.toEpochMilli(),
        endDate = endDate.toEpochMilli(),
        active = active
    )
}