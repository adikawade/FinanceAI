package com.financeai.app.data.repository

import com.financeai.app.data.local.dao.GoalDao
import com.financeai.app.data.mapper.toDomain
import com.financeai.app.data.mapper.toEntity
import com.financeai.app.domain.model.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val goalDao: GoalDao
) : GoalRepository {

    override fun getAllGoals(): Flow<List<Goal>> {
        return goalDao
            .getAllGoals()
            .map { goals ->
                goals.map { it.toDomain() }
            }
    }

    override suspend fun getGoalById(id: String): Goal? {
        return goalDao
            .getGoalById(id)
            ?.toDomain()
    }

    override suspend fun insert(goal: Goal) {
        goalDao.insert(goal.toEntity())
    }

    override suspend fun insertAll(goals: List<Goal>) {
        goalDao.insertAll(
            goals.map { it.toEntity() }
        )
    }

    override suspend fun update(goal: Goal) {
        goalDao.update(goal.toEntity())
    }

    override suspend fun delete(goal: Goal) {
        goalDao.delete(goal.toEntity())
    }

    override suspend fun deleteAll() {
        goalDao.deleteAll()
    }
}