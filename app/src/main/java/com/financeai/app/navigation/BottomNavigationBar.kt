package com.financeai.app.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FinanceAIBottomNavigation(currentRoute: String, onNavigate: (String) -> Unit) {
    NavigationBar {
        BottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = { Text(item.label.take(1)) },
                label = { Text(item.label) }
            )
        }
    }
}
