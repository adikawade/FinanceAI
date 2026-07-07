package com.financeai.app.di

import com.financeai.app.data.repository.TransactionRepository
import com.financeai.app.domain.usecase.transaction.DeleteAllTransactionsUseCase
import com.financeai.app.domain.usecase.transaction.DeleteTransactionUseCase
import com.financeai.app.domain.usecase.transaction.GetTransactionByIdUseCase
import com.financeai.app.domain.usecase.transaction.GetTransactionsUseCase
import com.financeai.app.domain.usecase.transaction.InsertTransactionUseCase
import com.financeai.app.domain.usecase.transaction.UpdateTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetTransactionsUseCase(
        repository: TransactionRepository
    ) = GetTransactionsUseCase(repository)

    @Provides
    fun provideGetTransactionByIdUseCase(
        repository: TransactionRepository
    ) = GetTransactionByIdUseCase(repository)

    @Provides
    fun provideInsertTransactionUseCase(
        repository: TransactionRepository
    ) = InsertTransactionUseCase(repository)

    @Provides
    fun provideUpdateTransactionUseCase(
        repository: TransactionRepository
    ) = UpdateTransactionUseCase(repository)

    @Provides
    fun provideDeleteTransactionUseCase(
        repository: TransactionRepository
    ) = DeleteTransactionUseCase(repository)

    @Provides
    fun provideDeleteAllTransactionsUseCase(
        repository: TransactionRepository
    ) = DeleteAllTransactionsUseCase(repository)
}