package com.devstone.punchesmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class ToolRecord (
    @PrimaryKey(autoGenerate = true) val toolRecordId: Long,
    val toolSetId: String,
    val productId: String,
    val productName: String,
    val roomNumber: Int,
    var dosesRan: Long,
    var checkStatus: Boolean = false
    ) {

}