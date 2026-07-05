package com.financeai.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.financeai.app.ui.screens.budget.BudgetScreen
import com.financeai.app.ui.screens.home.HomeScreen
import com.financeai.app.ui.screens.investments.InvestmentsScreen
import com.financeai.app.ui.screens.login.LoginScreen
import com.financeai.app.ui.screens.profile.ProfileScreen
import com.financeai.app.ui.screens.settings.SettingsScreen
import com.financeai.app.ui.screens.splash.SplashScreen
import com.financeai.app.ui.screens.tax.TaxScreen
import com.financeai.app.ui.screens.transactions.TransactionsScreen

@Composable
fun FinanceAINavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoutes.SPLASH) {
        composable(NavigationRoutes.SPLASH) { SplashScreen() }
        composable(NavigationRoutes.LOGIN) { LoginScreen() }
        composable(NavigationRoutes.HOME) { HomeScreen() }
        composable(NavigationRoutes.TRANSACTIONS) { TransactionsScreen() }
        composable(NavigationRoutes.BUDGET) { BudgetScreen() }
        composable(NavigationRoutes.INVESTMENTS) { InvestmentsScreen() }
        composable(NavigationRoutes.TAX) { TaxScreen() }
        composable(NavigationRoutes.PROFILE) { ProfileScreen() }
        composable(NavigationRoutes.SETTINGS) { SettingsScreen() }
    }
}