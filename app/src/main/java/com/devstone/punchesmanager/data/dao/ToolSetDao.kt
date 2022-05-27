package com.devstone.punchesmanager.data.dao


import androidx.room.*
import com.devstone.punchesmanager.data.entities.tool.ToolSet
import kotlinx.coroutines.flow.Flow


@Dao
interface ToolSetDao {

    @Query("SELECT * FROM tool_sets")
    fun getAllToolSets(): Flow<List<ToolSet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToolSet(toolSet: ToolSet)

    @Update
    suspend fun updateToolSet(toolSet: ToolSet)

    @Delete
    suspend fun deleteToolSet(toolSet: ToolSet)

}