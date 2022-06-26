package com.devstone.punchesmanager.ui.record

import com.devstone.punchesmanager.data.entities.Product

sealed class AddEditRecordEvent {
    data class OnDosesRanChange(val doses: Long): AddEditRecordEvent()
    data class OnProductClick(val product: Product): AddEditRecordEvent()
    data class OnRoomNumberChange(val roomNumber: Int): AddEditRecordEvent()
    object OnSaveRecordClick: AddEditRecordEvent()
    object OnProfileClick: AddEditRecordEvent()
}