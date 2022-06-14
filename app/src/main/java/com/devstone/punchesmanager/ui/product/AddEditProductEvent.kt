package com.devstone.punchesmanager.ui.product

sealed class AddEditProductEvent {
    data class OnCreateProductId(val productId: String): AddEditProductEvent()
    data class OnProductNameChange(val name: String): AddEditProductEvent()
    object OnSaveProductClick: AddEditProductEvent()
}