package com.financeai.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val targetAmount: Double,

    val currentAmount: Double,

    val targetDate: Long,

    val category: String,

    val completed: Boolean = false
)