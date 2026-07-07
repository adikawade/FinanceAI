package com.financeai.app.domain.usecase.transaction

import com.financeai.app.data.repository.TransactionRepository
import com.financeai.app.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase(
    private val repository: TransactionRepository
) {

    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getAllTransactions()
    }
}