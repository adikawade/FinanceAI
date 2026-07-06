package com.financeai.app.domain.model

enum class Category(
    val displayName: String,
    val icon: String
) {
    FOOD("Food", "🍔"),
    TRANSPORT("Transport", "🚗"),
    SHOPPING("Shopping", "🛍️"),
    ENTERTAINMENT("Entertainment", "🎬"),
    HEALTH("Health", "🏥"),
    EDUCATION("Education", "📚"),
    INVESTMENT("Investment", "📈"),
    SALARY("Salary", "💰"),
    BILLS("Bills", "🧾"),
    TRANSFER("Transfer", "🔁"),
    TAX("Tax", "🏛️"),
    CASH("Cash", "💵"),
    OTHER("Other", "📦")
}