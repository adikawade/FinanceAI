package com.financeai.app.di

import com.financeai.app.data.local.database.FinanceDatabase
import com.financeai.app.data.repository.BudgetRepository
import com.financeai.app.data.repository.BudgetRepositoryImpl
import com.financeai.app.data.repository.GoalRepository
import com.financeai.app.data.repository.GoalRepositoryImpl
import com.financeai.app.data.repository.TransactionRepository
import com.financeai.app.data.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTransactionRepository(
        database: FinanceDatabase
    ): TransactionRepository {
        return TransactionRepositoryImpl(
            database.transactionDao()
        )
    }

    @Provides
    @Singleton
    fun provideBudgetRepository(
        database: FinanceDatabase
    ): BudgetRepository {
        return BudgetRepositoryImpl(
            database.budgetDao()
        )
    }

    @Provides
    @Singleton
    fun provideGoalRepository(
        database: FinanceDatabase
    ): GoalRepository {
        return GoalRepositoryImpl(
            database.goalDao()
        )
    }
}