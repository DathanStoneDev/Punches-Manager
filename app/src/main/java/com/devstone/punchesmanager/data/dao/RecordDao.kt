package com.devstone.punchesmanager.data.dao

import androidx.room.*
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.entities.ToolSet
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM records")
    fun getAllRecords(): Flow<List<ToolRecord>>

    @Query("SELECT * FROM records WHERE toolRecordId =:toolRecordId")
    suspend fun getRecordById(toolRecordId: Long): ToolRecord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: ToolRecord)

    @Update
    suspend fun updateRecord(record: ToolRecord)

    @Delete
    suspend fun deleteRecord(record: ToolRecord)

    @Query("SELECT * FROM records WHERE toolSetId =:toolSetId")
    suspend fun getToolSetsById(toolSetId: String): List<ToolRecord>
}