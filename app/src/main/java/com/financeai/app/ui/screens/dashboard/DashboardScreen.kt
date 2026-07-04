package com.financeai.app.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
        Card { Text("Net Worth", modifier = Modifier.padding(16.dp)) }
        Card { Text("Financial Health Score", modifier = Modifier.padding(16.dp)) }
        Card { Text("Recent Transactions", modifier = Modifier.padding(16.dp)) }
    }
}
