package com.financeai.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FinanceColorScheme = lightColorScheme(
    primary = FinancePrimary,
    secondary = FinanceSecondary,
    background = FinanceBackground,
    surface = FinanceSurface,
    error = FinanceError
)

@Composable
fun FinanceAITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FinanceColorScheme,
        typography = FinanceTypography,
        shapes = FinanceShapes,
        content = content
    )
}
