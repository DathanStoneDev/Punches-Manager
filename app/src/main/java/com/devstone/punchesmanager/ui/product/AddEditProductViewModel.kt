package com.devstone.punchesmanager.ui.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.entities.Product
import com.devstone.punchesmanager.data.repository.ProductRepository
import com.devstone.punchesmanager.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var product by mutableStateOf<Product?>(null)
        private set

    var productId by mutableStateOf("")
        private set

    var name by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        val sentProductId = savedStateHandle.get<String>("productId")!!
        productId = sentProductId
        if (productId != ""){
            viewModelScope.launch {
                repository.getProductById(productId)?.let { product ->
                    productId = sentProductId
                    name = product.name
                    this@AddEditProductViewModel.product = product
                }
            }
        }
    }


    fun onEvent(event: AddEditProductEvent) {
        when(event) {
            is AddEditProductEvent.OnCreateProductId -> {
                productId = event.productId
            }
            is AddEditProductEvent.OnProductNameChange -> {
                name = event.name
            }
            is AddEditProductEvent.OnSaveProductClick -> {
                viewModelScope.launch {
                    if (productId.isNotBlank()) {
                        repository.insertProduct(
                            Product(
                                productId = productId,
                                name = name
                            )
                        )
                        sendUiEvent(UiEvent.PopBackStack)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}