package com.devstone.punchesmanager.ui.product

/**
 * Contains product events when a product is added/edited.
 */
sealed class AddEditProductEvent {
    data class OnCreateProductId(val productId: String): AddEditProductEvent()
    data class OnProductNameChange(val name: String): AddEditProductEvent()
    object OnSaveProductClick: AddEditProductEvent()
}