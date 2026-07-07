package com.financeai.app.domain.usecase.transaction

import com.financeai.app.data.repository.TransactionRepository

class DeleteAllTransactionsUseCase(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke() {
        repository.deleteAll()
    }
}