package com.financeai.app.domain.usecase.transaction

import com.financeai.app.data.repository.TransactionRepository
import com.financeai.app.domain.model.Transaction

class GetTransactionByIdUseCase(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke(id: String): Transaction? {
        return repository.getTransactionById(id)
    }
}