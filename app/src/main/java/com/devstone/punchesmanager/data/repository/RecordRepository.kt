package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.entities.ToolRecord
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getAllRecords(): Flow<List<ToolRecord>>

    suspend fun getRecordById(toolRecordId: Long): ToolRecord?

    suspend fun insertRecord(record: ToolRecord)

    suspend fun updateRecord(record: ToolRecord)

    suspend fun deleteRecord(record: ToolRecord)
}