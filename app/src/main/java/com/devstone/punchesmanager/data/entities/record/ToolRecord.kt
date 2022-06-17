package com.devstone.punchesmanager.data.entities.record

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToolRecord (
    @PrimaryKey(autoGenerate = true) val toolRecordId: Long,
    val toolSetId: String,
    val productId: String,
    val productName: String,
    val roomNumber: Int,
    var checkStatus: Boolean = false
    ) {

}