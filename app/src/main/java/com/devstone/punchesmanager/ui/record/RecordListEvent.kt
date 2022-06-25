package com.devstone.punchesmanager.ui.record

import com.devstone.punchesmanager.data.entities.ToolRecord

sealed class RecordListEvent {
    data class OnDeleteRecord(val record: ToolRecord): RecordListEvent()
    data class OnRecordClick(val record: ToolRecord): RecordListEvent()
    data class OnRecordStatusChange(val status: Boolean, val record: ToolRecord): RecordListEvent()
}