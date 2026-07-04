package com.financeai.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text

@Composable
fun FinanceAINavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.SPLASH) {
        composable(NavigationRoutes.SPLASH) { Text("Splash") }
        composable(NavigationRoutes.LOGIN) { Text("Login") }
        composable(NavigationRoutes.HOME) { Text("Home") }
        composable(NavigationRoutes.TRANSACTIONS) { Text("Transactions") }
        composable(NavigationRoutes.BUDGET) { Text("Budget") }
        composable(NavigationRoutes.INVESTMENTS) { Text("Investments") }
        composable(NavigationRoutes.TAX) { Text("Tax") }
        composable(NavigationRoutes.PROFILE) { Text("Profile") }
        composable(NavigationRoutes.SETTINGS) { Text("Settings") }
    }
}
