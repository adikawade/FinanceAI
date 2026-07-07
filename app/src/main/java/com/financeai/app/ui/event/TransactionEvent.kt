package com.financeai.app.ui.event

import com.financeai.app.domain.model.Transaction

sealed interface TransactionEvent {

    data object LoadTransactions : TransactionEvent

    data class AddTransaction(
        val transaction: Transaction
    ) : TransactionEvent

    data class UpdateTransaction(
        val transaction: Transaction
    ) : TransactionEvent

    data class DeleteTransaction(
        val transaction: Transaction
    ) : TransactionEvent

    data object DeleteAllTransactions : TransactionEvent

    data class SelectTransaction(
        val transaction: Transaction
    ) : TransactionEvent
}