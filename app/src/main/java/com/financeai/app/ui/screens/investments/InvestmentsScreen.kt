package com.financeai.app.ui.screens.investments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InvestmentsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Investments", style = MaterialTheme.typography.headlineMedium)
        Text("Portfolio tracking, SIPs and AI investment insights will appear here.")
    }
}
