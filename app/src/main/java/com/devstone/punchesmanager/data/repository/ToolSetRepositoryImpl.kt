package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.tool.ToolSet
import kotlinx.coroutines.flow.Flow

class ToolSetRepositoryImpl(
    private val dao: ToolSetDao
): ToolSetRepository {

    override fun getAllToolSets(): Flow<List<ToolSet>> {
        return dao.getAllToolSets()
    }

    override suspend fun getToolSetByPO(poNumber: String): ToolSet {
        return dao.getToolSetByPO(poNumber)
    }

    override suspend fun insertToolSet(toolSet: ToolSet) {
        dao.insertToolSet(toolSet)
    }

    override suspend fun updateToolSet(toolSet: ToolSet) {
        dao.updateToolSet(toolSet)
    }

    override suspend fun deleteToolSet(toolSet: ToolSet) {
        dao.deleteToolSet(toolSet)
    }
}