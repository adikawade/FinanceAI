package com.financeai.app.data.repository

import com.financeai.app.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions(): Flow<List<Transaction>>

    suspend fun getTransactionById(id: String): Transaction?

    suspend fun insert(transaction: Transaction)

    suspend fun insertAll(transactions: List<Transaction>)

    suspend fun update(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun deleteAll()
}