package com.financeai.app.ui.screens.tax

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaxScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Tax", style = MaterialTheme.typography.headlineMedium)
        Text("ITR guidance, tax planning and AI recommendations will appear here.")
    }
}
