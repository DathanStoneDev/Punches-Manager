package com.devstone.punchesmanager.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI and Business logic handled here for the HomeScreen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var username by mutableStateOf("")
        private set

    init {
        val sentUsername = savedStateHandle.get<String>("username")
        if (sentUsername != null) {
            username = sentUsername
        }
    }

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
            is HomeEvent.OnProfileClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.PROFILE + "?username=${username}"))
            }
            is HomeEvent.OnReportCardClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.REPORTS))
            }

        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

