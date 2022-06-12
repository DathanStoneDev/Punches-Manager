package com.devstone.punchesmanager.ui.toolset

sealed class AddEditToolSetEvent {
    data class OnCreatePONumber(val poNumber: String): AddEditToolSetEvent()
    data class OnAmountChange(val amount: Int): AddEditToolSetEvent()
    data class OnShapeChange(val shape: String): AddEditToolSetEvent()
    data class OnCostChange(val cost: Double): AddEditToolSetEvent()
    data class OnLifeExpectancyChange(val lifeExpectancy: Int): AddEditToolSetEvent()
    data class OnTipTypeChange(val toolTipType: Int): AddEditToolSetEvent()
    object OnSaveToolSetClick: AddEditToolSetEvent()
}
