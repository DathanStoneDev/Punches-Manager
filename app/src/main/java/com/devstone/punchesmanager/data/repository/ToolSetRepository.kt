package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.entities.ToolSet
import kotlinx.coroutines.flow.Flow

interface ToolSetRepository {

    fun getAllToolSets(): Flow<List<ToolSet>>

    suspend fun getToolSetByPO(poNumber: String): ToolSet?

    suspend fun insertToolSet(toolSet: ToolSet)

    suspend fun updateToolSet(toolSet: ToolSet)

    suspend fun deleteToolSet(toolSet: ToolSet)
}