package com.devstone.punchesmanager.ui.record

import com.devstone.punchesmanager.data.entities.Product

sealed class AddRecordEvent {
    data class OnDosesRanChange(val doses: Long): AddRecordEvent()
    data class OnProductClick(val product: Product): AddRecordEvent()
    data class OnRoomNumberChange(val roomNumber: Int): AddRecordEvent()
    object OnSaveRecordClick: AddRecordEvent()
    data class OnDateClick(val date: String): AddRecordEvent()
    data class OnTimeClick(val time: String): AddRecordEvent()
}