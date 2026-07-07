package com.financeai.app.data.repository

import com.financeai.app.data.local.dao.BudgetDao
import com.financeai.app.data.mapper.toDomain
import com.financeai.app.data.mapper.toEntity
import com.financeai.app.domain.model.Budget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetRepositoryImpl(
    private val budgetDao: BudgetDao
) : BudgetRepository {

    override fun getAllBudgets(): Flow<List<Budget>> {
        return budgetDao
            .getAllBudgets()
            .map { budgets ->
                budgets.map { it.toDomain() }
            }
    }

    override suspend fun getBudgetById(id: String): Budget? {
        return budgetDao
            .getBudgetById(id)
            ?.toDomain()
    }

    override suspend fun insert(budget: Budget) {
        budgetDao.insert(budget.toEntity())
    }

    override suspend fun insertAll(budgets: List<Budget>) {
        budgetDao.insertAll(
            budgets.map { it.toEntity() }
        )
    }

    override suspend fun update(budget: Budget) {
        budgetDao.update(budget.toEntity())
    }

    override suspend fun delete(budget: Budget) {
        budgetDao.delete(budget.toEntity())
    }

    override suspend fun deleteAll() {
        budgetDao.deleteAll()
    }
}