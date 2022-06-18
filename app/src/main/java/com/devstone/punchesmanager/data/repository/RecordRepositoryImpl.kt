package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.dao.RecordDao
import com.devstone.punchesmanager.data.entities.record.ToolRecord
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl (
    private val dao: RecordDao
        ): RecordRepository {

    override fun getAllRecords(): Flow<List<ToolRecord>> {
        return dao.getAllRecords()
    }

    override suspend fun getRecordById(toolRecordId: Long): ToolRecord? {
        return dao.getRecordById(toolRecordId)
    }

    override suspend fun insertRecord(record: ToolRecord) {
        return dao.insertRecord(record)
    }

    override suspend fun updateRecord(record: ToolRecord) {
        return dao.updateRecord(record)
    }

    override suspend fun deleteRecord(record: ToolRecord) {
        return dao.deleteRecord(record)
    }

}