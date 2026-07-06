package com.financeai.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.financeai.app.data.local.dao.TransactionDao
import com.financeai.app.data.local.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}