package com.financeai.app.navigation

sealed class FinanceAIDestination(val route: String) {
    data object Splash : FinanceAIDestination(NavigationRoutes.SPLASH)
    data object Login : FinanceAIDestination(NavigationRoutes.LOGIN)
    data object Home : FinanceAIDestination(NavigationRoutes.HOME)
    data object Transactions : FinanceAIDestination(NavigationRoutes.TRANSACTIONS)
    data object Budget : FinanceAIDestination(NavigationRoutes.BUDGET)
    data object Investments : FinanceAIDestination(NavigationRoutes.INVESTMENTS)
    data object Tax : FinanceAIDestination(NavigationRoutes.TAX)
    data object Profile : FinanceAIDestination(NavigationRoutes.PROFILE)
    data object Settings : FinanceAIDestination(NavigationRoutes.SETTINGS)
}
