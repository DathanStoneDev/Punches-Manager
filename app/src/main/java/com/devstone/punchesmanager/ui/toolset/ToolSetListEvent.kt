package com.devstone.punchesmanager.ui.toolset

import com.devstone.punchesmanager.data.entities.tool.ToolSet

sealed class ToolSetListEvent {
    data class OnDeleteToolSetClick(val toolset: ToolSet): ToolSetListEvent()
    data class OnToolSetClick(val toolSet: ToolSet): ToolSetListEvent()
    data class OnCreateRecordClick(val PONumber: String): ToolSetListEvent()
    object OnAddToolSetClick: ToolSetListEvent()
}
