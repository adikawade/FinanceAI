package com.financeai.app.ui.action

sealed interface TransactionAction {

    data class ShowMessage(
        val message: String
    ) : TransactionAction

    data object NavigateBack : TransactionAction

    data object OpenAddTransaction : TransactionAction

    data class OpenTransactionDetails(
        val transactionId: String
    ) : TransactionAction
}