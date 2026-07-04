package com.financeai.app.navigation

import androidx.navigation.NavHostController

/**
 * Temporary navigation wiring module.
 * This will later be replaced with Hilt-provided navigation dependencies.
 */
object NavigationModule {
    fun provideNavigator(navController: NavHostController): AppNavigator {
        return AppNavigator(navController)
    }
}
