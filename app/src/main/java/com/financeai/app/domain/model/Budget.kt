package com.financeai.app.domain.model

import java.time.Instant

data class Budget(

    val id: String,

    val category: Category,

    val monthlyLimit: Double,

    val currentSpent: Double = 0.0,

    val startDate: Instant,

    val endDate: Instant,

    val active: Boolean = true

) {

    val remaining: Double
        get() = monthlyLimit - currentSpent

    val percentageUsed: Float
        get() =
            if (monthlyLimit == 0.0) 0f
            else ((currentSpent / monthlyLimit) * 100).toFloat()
}