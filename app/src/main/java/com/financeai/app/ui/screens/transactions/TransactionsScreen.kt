package com.financeai.app.ui.screens.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransactionsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Transactions", style = MaterialTheme.typography.headlineMedium)
        Text("Your transaction history and AI categorization will appear here.")
    }
}
