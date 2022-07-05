package com.devstone.punchesmanager.ui.toolset

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.entities.ToolSet
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import com.devstone.punchesmanager.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditToolSetViewModel @Inject constructor(
    private val repository: ToolSetRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var toolSet by mutableStateOf<ToolSet?>(null)
        private set

    var PONumber by mutableStateOf("")
        private set

    var amount by mutableStateOf(0)
        private set

    var shape by mutableStateOf("")
        private set

    var cost by mutableStateOf(0.0)
        private set

    var life by mutableStateOf(0)
        private set

    var tipType by mutableStateOf(1)
        private set

    var retired by mutableStateOf(false)
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val toolSetPONumber = savedStateHandle.get<String>("PONumber")!!
        if (toolSetPONumber != ""){
            viewModelScope.launch {
                repository.getToolSetByPO(toolSetPONumber)?.let { toolSet ->
                    PONumber = toolSet.PONumber
                    amount = toolSet.setAmount
                    shape = toolSet.shape
                    cost = toolSet.cost
                    life = toolSet.lifeExpectancy
                    tipType = toolSet.tipType
                    retired = toolSet.retired
                    this@AddEditToolSetViewModel.toolSet = toolSet
                }

            }
        }
    }

    fun onEvent(event: AddEditToolSetEvent) {
        when(event){
            is AddEditToolSetEvent.OnCreatePONumber -> {
                PONumber = event.poNumber
            }
            is AddEditToolSetEvent.OnAmountChange -> {
                amount = event.amount
            }
            is AddEditToolSetEvent.OnCostChange -> {
                cost = event.cost
            }
            is AddEditToolSetEvent.OnShapeChange -> {
                shape = event.shape
            }
            is AddEditToolSetEvent.OnLifeExpectancyChange -> {
                life = event.lifeExpectancy
            }
            is AddEditToolSetEvent.OnTipTypeChange -> {
                tipType = event.toolTipType
            }
            is AddEditToolSetEvent.OnRetireSelection -> {
                retired = event.retire
            }
            is AddEditToolSetEvent.OnSaveToolSetClick -> {
                viewModelScope.launch {
                    if (PONumber.isNotBlank()) {
                        repository.insertToolSet(
                            ToolSet(
                                PONumber = PONumber,
                                tipType = tipType,
                                setAmount = amount,
                                shape = shape,
                                lifeExpectancy = life,
                                cost = cost,
                                retired = retired
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