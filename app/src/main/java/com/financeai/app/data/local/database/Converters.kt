package com.financeai.app.data.local.database

import androidx.room.TypeConverter
import com.financeai.app.domain.model.Category
import com.financeai.app.domain.model.TransactionType
import java.time.Instant

class Converters {

    @TypeConverter
    fun fromInstant(value: Instant?): Long? {
        return value?.toEpochMilli()
    }

    @TypeConverter
    fun toInstant(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(it) }
    }

    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return category?.name
    }

    @TypeConverter
    fun toCategory(value: String?): Category? {
        return value?.let {
            runCatching { Category.valueOf(it) }
                .getOrDefault(Category.OTHER)
        }
    }

    @TypeConverter
    fun fromTransactionType(type: TransactionType?): String? {
        return type?.name
    }

    @TypeConverter
    fun toTransactionType(value: String?): TransactionType? {
        return value?.let {
            runCatching { TransactionType.valueOf(it) }
                .getOrDefault(TransactionType.EXPENSE)
        }
    }
}