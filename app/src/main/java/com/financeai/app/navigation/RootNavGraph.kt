package com.financeai.app.navigation

import androidx.compose.runtime.Composable

/**
 * Root navigation graph entry point.
 * This will later orchestrate auth and main graphs.
 */
@Composable
fun RootNavGraph() {
    FinanceAINavHost()
}
