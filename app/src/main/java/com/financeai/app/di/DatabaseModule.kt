package com.financeai.app.di

import android.content.Context
import androidx.room.Room
import com.financeai.app.data.local.database.FinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFinanceDatabase(
        @ApplicationContext context: Context
    ): FinanceDatabase {

        return Room.databaseBuilder(
            context,
            FinanceDatabase::class.java,
            "finance_ai_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}