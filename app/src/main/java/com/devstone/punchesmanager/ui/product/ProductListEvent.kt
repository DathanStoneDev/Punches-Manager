package com.devstone.punchesmanager.ui.product

import com.devstone.punchesmanager.data.entities.Product

sealed class ProductListEvent {
    data class OnDeleteProductClick(val product: Product): ProductListEvent()
    data class OnProductClick(val product: Product): ProductListEvent()
    data class OnSearchEvent(val searchText: String): ProductListEvent()
    object OnAddEditProductClick: ProductListEvent()
}