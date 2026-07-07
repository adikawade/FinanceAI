package com.financeai.app.data.repository

import com.financeai.app.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getAllBudgets(): Flow<List<Budget>>

    suspend fun getBudgetById(id: String): Budget?

    suspend fun insert(budget: Budget)

    suspend fun insertAll(budgets: List<Budget>)

    suspend fun update(budget: Budget)

    suspend fun delete(budget: Budget)

    suspend fun deleteAll()
}