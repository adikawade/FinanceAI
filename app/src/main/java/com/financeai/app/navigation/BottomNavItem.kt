package com.financeai.app.navigation

data class BottomNavItem(
    val route: String,
    val label: String
)

val BottomNavigationItems = listOf(
    BottomNavItem(NavigationRoutes.HOME, "Home"),
    BottomNavItem(NavigationRoutes.TRANSACTIONS, "Transactions"),
    BottomNavItem(NavigationRoutes.BUDGET, "Budget"),
    BottomNavItem(NavigationRoutes.PROFILE, "Profile")
)
