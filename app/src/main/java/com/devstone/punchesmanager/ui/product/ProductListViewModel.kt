package com.devstone.punchesmanager.ui.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.repository.ProductRepository
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor (
    private val repository: ProductRepository
    ): ViewModel() {

    val products = repository.getAllProducts()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProductListEvent){
        when(event) {
            is ProductListEvent.OnProductClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.PRODUCT_ADD_EDIT + "?productId=${event.product.productId}"))
            }
            is ProductListEvent.OnDeleteProductClick -> {
                viewModelScope.launch {
                    repository.deleteProduct(event.product)
                }
            }
            is ProductListEvent.OnAddEditProductClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.PRODUCT_ADD_EDIT))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    }