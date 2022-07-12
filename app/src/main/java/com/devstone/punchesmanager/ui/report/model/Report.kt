package com.devstone.punchesmanager.ui.report.model

import androidx.room.Embedded
import androidx.room.Relation
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.entities.ToolSet

/**
 * Embedded object to retrieve toolsets and their records based on tool set id.
 */
data class ToolSetReport(
    @Embedded val toolSet: ToolSet,
    @Relation(
        parentColumn = "PONumber",
        entityColumn = "toolSetId"
    )
    val toolRecords: List<ToolRecord>?
)
