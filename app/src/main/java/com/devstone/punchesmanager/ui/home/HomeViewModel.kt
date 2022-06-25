package com.devstone.punchesmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()



    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnToolSetCardClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.TOOL_SET_LIST))
            }
            is HomeEvent.OnProductCardClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.PRODUCT_LIST))
            }
            is HomeEvent.OnRecordCardClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.RECORD_LIST))
            }

        }

    }









    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

