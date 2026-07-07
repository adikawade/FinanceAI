package com.financeai.app.data.repository

import com.financeai.app.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getAllGoals(): Flow<List<Goal>>

    suspend fun getGoalById(id: String): Goal?

    suspend fun insert(goal: Goal)

    suspend fun insertAll(goals: List<Goal>)

    suspend fun update(goal: Goal)

    suspend fun delete(goal: Goal)

    suspend fun deleteAll()
}