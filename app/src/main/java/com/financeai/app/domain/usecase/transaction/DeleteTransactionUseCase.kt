package com.financeai.app.domain.usecase.transaction

import com.financeai.app.data.repository.TransactionRepository
import com.financeai.app.domain.model.Transaction

class DeleteTransactionUseCase(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke(transaction: Transaction) {
        repository.delete(transaction)
    }
}