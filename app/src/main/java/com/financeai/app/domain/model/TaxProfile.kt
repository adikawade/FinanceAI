package com.financeai.app.domain.model

data class TaxProfile(

    val userId: String,

    val financialYear: String,

    val regime: TaxRegime,

    val employmentType: EmploymentType,

    val annualIncome: Double,

    val taxableIncome: Double,

    val estimatedTax: Double,

    val deductions: Double = 0.0,

    val taxPaid: Double = 0.0

) {

    val remainingTax: Double
        get() = estimatedTax - taxPaid
}

enum class TaxRegime {
    OLD,
    NEW
}

enum class EmploymentType {
    SALARIED,
    SELF_EMPLOYED,
    BUSINESS,
    FREELANCER,
    RETIRED,
    OTHER
}