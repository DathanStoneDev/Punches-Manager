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
import java.lang.NumberFormatException
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

    var amount by mutableStateOf("")
        private set

    var shape by mutableStateOf("")
        private set

    var cost by mutableStateOf("")
        private set

    var tipType by mutableStateOf("")
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
                    amount = toolSet.setAmount.toString()
                    shape = toolSet.shape
                    cost = toolSet.cost.toString()
                    tipType = toolSet.tipType.toString()
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
            is AddEditToolSetEvent.OnTipTypeChange -> {
                tipType = event.toolTipType
            }
            is AddEditToolSetEvent.OnRetireSelection -> {
                retired = event.retire
            }
            is AddEditToolSetEvent.OnSaveToolSetClick -> {
                val approved: Boolean = if (!verifyTextFieldInputForInt(tipType, amount)){
                    false
                } else verifyTextFieldInputForDouble(cost)

                viewModelScope.launch {
                    if (PONumber.isNotBlank() && approved) {
                        repository.insertToolSet(
                            ToolSet(
                                PONumber = PONumber,
                                tipType = tipType.toInt(),
                                setAmount = amount.toInt(),
                                shape = shape,
                                lifeExpectancy = calculateLifeExpectancy(),
                                cost = cost.toDouble(),
                                retired = retired
                            )
                        )
                        sendUiEvent(UiEvent.PopBackStack)
                    }
                }
            }


        }
    }

    /**
     * Auto-calculates the life expectancy of a set based on the amount of punches multiplied by
     * a million.
     */
    private fun calculateLifeExpectancy(): Int {
        return amount.toInt() * 1000000
    }

    /**
     * Verifies that the tipType and Amount are ints.
     * Input are strings from the text fields and converted to ints.
     * Number format exception would be thrown if string cannot be converted to int. Returns false.
     */
    private fun verifyTextFieldInputForInt(tip: String, amount: String): Boolean {
        return try {
            tip.toInt()
            amount.toInt()
            true
        } catch (ex: NumberFormatException){
            false
        }
    }

    /**
     * Verifies that the cost is a double.
     * Input are strings from the cost text field and converted to a double.
     * Number format exception would be thrown if string cannot be converted to a double. Returns false.
     */
    private fun verifyTextFieldInputForDouble(cost: String): Boolean {
        return try{
            cost.toDouble()
            true
        } catch (ex: NumberFormatException){
            false
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}