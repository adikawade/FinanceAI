package com.financeai.app.data.repository

import com.financeai.app.data.local.dao.TransactionDao
import com.financeai.app.data.mapper.toDomain
import com.financeai.app.data.mapper.toEntity
import com.financeai.app.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao
            .getAllTransactions()
            .map { list ->
                list.map { it.toDomain() }
            }
    }

    override suspend fun getTransactionById(id: String): Transaction? {
        return transactionDao
            .getTransactionById(id)
            ?.toDomain()
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction.toEntity())
    }

    override suspend fun insertAll(transactions: List<Transaction>) {
        transactionDao.insertAll(
            transactions.map { it.toEntity() }
        )
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction.toEntity())
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction.toEntity())
    }

    override suspend fun deleteAll() {
        transactionDao.deleteAll()
    }
}
