package com.devstone.punchesmanager.ui.report.model

import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.entities.ToolSet

data class ToolSetReport(
    val toolSets: List<ToolSet>?,
    val toolRecords: List<ToolRecord>?
)

//Need report for the following: get all records for toolsets by PO# and then show total doses
//Need report for the following: get all records that are retired - show if they met their lifespan
