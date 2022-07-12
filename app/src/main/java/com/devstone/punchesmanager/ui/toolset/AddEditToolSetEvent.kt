package com.devstone.punchesmanager.ui.toolset

/**
 * Events that can occur on the add/edit pages for toolsets.
 */
sealed class AddEditToolSetEvent {
    data class OnCreatePONumber(val poNumber: String): AddEditToolSetEvent()
    data class OnAmountChange(val amount: String): AddEditToolSetEvent()
    data class OnShapeChange(val shape: String): AddEditToolSetEvent()
    data class OnCostChange(val cost: String): AddEditToolSetEvent()
    data class OnTipTypeChange(val toolTipType: String): AddEditToolSetEvent()
    object OnSaveToolSetClick: AddEditToolSetEvent()
    data class OnRetireSelection(val retire: Boolean): AddEditToolSetEvent()
}
