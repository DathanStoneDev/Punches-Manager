package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.tool.ToolSet
import kotlinx.coroutines.flow.Flow

class ToolSetRepository (private val toolSetDao: ToolSetDao) {

    val allToolSets: Flow<List<ToolSet>> = toolSetDao.getAllToolSets()

    suspend fun insert(toolSet: ToolSet) {
        toolSetDao.insertToolSet(toolSet)
    }

    suspend fun update(toolSet: ToolSet) {
        toolSetDao.updateToolSet(toolSet)
    }

    suspend fun delete(toolSet: ToolSet) {
        toolSetDao.deleteToolSet(toolSet)
    }
}