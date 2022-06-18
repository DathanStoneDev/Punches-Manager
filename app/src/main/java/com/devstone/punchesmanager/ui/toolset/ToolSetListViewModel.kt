package com.devstone.punchesmanager.ui.toolset

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import com.devstone.punchesmanager.util.navigation.Routes
import com.devstone.punchesmanager.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToolSetListViewModel @Inject constructor(
    private val repository: ToolSetRepository
): ViewModel() {

    val toolSets = repository.getAllToolSets()
    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var searchText by mutableStateOf("")
        private set

    fun onEvent(event: ToolSetListEvent) {
        when(event) {
            is ToolSetListEvent.OnToolSetClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TOOL_SET + "?PONumber=${event.toolSet.PONumber}"))
            }
            is ToolSetListEvent.OnDeleteToolSetClick -> {
                viewModelScope.launch {
                    repository.deleteToolSet(event.toolset)
                }
            }
            is ToolSetListEvent.OnAddToolSetClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TOOL_SET))
            }
            is ToolSetListEvent.OnSearchToolSet -> {
                viewModelScope.launch {
                    searchText = event.searchText
                    repository.getToolSetByPO(event.searchText)
                }
            }

        }
    }

    private fun searchToolSetList(search: String) {

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}