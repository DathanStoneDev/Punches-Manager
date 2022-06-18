package com.devstone.punchesmanager.data.dao

import androidx.room.*
import com.devstone.punchesmanager.data.entities.record.ToolRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM products")
    fun getAllRecords(): Flow<List<ToolRecord>>

    @Query("SELECT * FROM records WHERE toolRecordId =:toolRecordId")
    suspend fun getRecordById(toolRecordId: Long): ToolRecord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: ToolRecord)

    @Update
    suspend fun updateRecord(record: ToolRecord)

    @Delete
    suspend fun deleteRecord(record: ToolRecord)
}