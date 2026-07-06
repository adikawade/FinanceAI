package com.financeai.app.domain.model

import java.time.Instant

data class Goal(

    val id: String,

    val title: String,

    val targetAmount: Double,

    val currentAmount: Double = 0.0,

    val targetDate: Instant,

    val category: Category,

    val completed: Boolean = false

) {

    val progress: Float
        get() =
            if (targetAmount == 0.0) 0f
            else ((currentAmount / targetAmount) * 100).toFloat()
}