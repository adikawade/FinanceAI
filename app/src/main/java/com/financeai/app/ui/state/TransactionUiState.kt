package com.financeai.app.ui.state

import com.financeai.app.domain.model.Transaction

data class TransactionUiState(

    val isLoading: Boolean = false,

    val transactions: List<Transaction> = emptyList(),

    val selectedTransaction: Transaction? = null,

    val error: String? = null
)