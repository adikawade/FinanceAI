package com.financeai.app.data.local.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: FinanceDatabase? = null

    fun getDatabase(context: Context): FinanceDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                FinanceDatabase::class.java,
                "finance_ai_database"
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance

            instance
        }
    }
}