package com.devstone.punchesmanager.ui.toolset

import com.devstone.punchesmanager.data.entities.ToolSet

sealed class ToolSetListEvent {
    data class OnDeleteToolSetClick(val toolset: ToolSet): ToolSetListEvent()
    data class OnToolSetClick(val toolSet: ToolSet): ToolSetListEvent()
    data class OnCreateRecordClick(val PONumber: String): ToolSetListEvent()
    data class OnSearchToolSet(val searchText: String): ToolSetListEvent()
    object OnAddToolSetClick: ToolSetListEvent()
    object OnProfileClick: ToolSetListEvent()
}
