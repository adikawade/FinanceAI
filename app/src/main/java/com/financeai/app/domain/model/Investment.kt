package com.financeai.app.domain.model

import java.time.Instant

data class Investment(

    val id: String,

    val name: String,

    val type: InvestmentType,

    val investedAmount: Double,

    val currentValue: Double,

    val purchaseDate: Instant,

    val platform: String = "",

    val notes: String = ""

) {

    val profitLoss: Double
        get() = currentValue - investedAmount

    val profitLossPercentage: Float
        get() =
            if (investedAmount == 0.0) 0f
            else (((currentValue - investedAmount) / investedAmount) * 100).toFloat()
}

enum class InvestmentType {
    STOCK,
    MUTUAL_FUND,
    ETF,
    GOLD,
    CRYPTO,
    FD,
    PPF,
    NPS,
    REAL_ESTATE,
    OTHER
}