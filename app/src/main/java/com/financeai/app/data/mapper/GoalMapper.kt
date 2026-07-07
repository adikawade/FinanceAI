package com.financeai.app.data.mapper

import com.financeai.app.data.local.entity.GoalEntity
import com.financeai.app.domain.model.Category
import com.financeai.app.domain.model.Goal
import java.time.Instant

fun GoalEntity.toDomain(): Goal {
    return Goal(
        id = id,
        title = title,
        targetAmount = targetAmount,
        currentAmount = currentAmount,
        targetDate = Instant.ofEpochMilli(targetDate),
        category = runCatching {
            Category.valueOf(category)
        }.getOrDefault(Category.OTHER),
        completed = completed
    )
}

fun Goal.toEntity(): GoalEntity {
    return GoalEntity(
        id = id,
        title = title,
        targetAmount = targetAmount,
        currentAmount = currentAmount,
        targetDate = targetDate.toEpochMilli(),
        category = category.name,
        completed = completed
    )
}