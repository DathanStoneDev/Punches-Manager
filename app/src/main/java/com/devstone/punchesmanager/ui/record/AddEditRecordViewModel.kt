package com.devstone.punchesmanager.ui.record

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.entities.Product
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.repository.ProductRepository
import com.devstone.punchesmanager.data.repository.RecordRepository
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import com.devstone.punchesmanager.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditRecordViewModel @Inject constructor(
    private val repository: RecordRepository,
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val products = productRepository.getAllProducts()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var product by mutableStateOf<Product?>(null)
        private set

    var record by mutableStateOf<ToolRecord?>(null)
        private set

    var toolRecordId: Long by mutableStateOf(0)
        private set

    var toolSetPONumberForRecord by mutableStateOf("")
        private set

    var productIdForRecord by mutableStateOf("")
        private set

    var productNameForRecord by mutableStateOf("")
        private set

    var roomNumber by mutableStateOf(0)
        private set

    var totalDoses: Long by mutableStateOf(0)
        private set

    var date by mutableStateOf("")
        private set

    init {

        val toolSetPONumber: String? = savedStateHandle.get<String>("PONumber")
        val recordId: Long? = savedStateHandle.get<Long>("tooRecordId")
        viewModelScope.launch {
            if (toolSetPONumber != "") {
                this@AddEditRecordViewModel.toolSetPONumberForRecord = toolSetPONumber!!
            } else {
                repository.getRecordById(recordId!!)?.let { record ->
                    this@AddEditRecordViewModel.toolSetPONumberForRecord = record.toolSetId
                    productIdForRecord = record.productId
                    productNameForRecord = record.productName
                    roomNumber = record.roomNumber
                    this@AddEditRecordViewModel.record = record
                    totalDoses = record.dosesRan
                    date = record.date
                }
            }
        }
    }

    fun onEvent(event: AddRecordEvent) {
        when(event) {
            is AddRecordEvent.OnProductClick -> {
                productIdForRecord = event.product.productId
                productNameForRecord = event.product.name
            }
            is AddRecordEvent.OnDosesRanChange -> {
                totalDoses = event.doses
            }
            is AddRecordEvent.OnRoomNumberChange -> {
                roomNumber = event.roomNumber
            }
            is AddRecordEvent.OnSaveRecordClick -> {
                date = event.dateTime
                viewModelScope.launch{
                    repository.insertRecord(
                        ToolRecord(
                            toolRecordId = toolRecordId,
                            toolSetId = toolSetPONumberForRecord,
                            productId = productIdForRecord,
                            productName = productNameForRecord,
                            roomNumber = roomNumber,
                            dosesRan = totalDoses,
                            date = date,
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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