package com.devstone.punchesmanager.ui.record

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.repository.RecordRepository
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(
    private val repository: RecordRepository
): ViewModel() {

    val records = repository.getAllRecords()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var checkStatus by mutableStateOf(false)
        private set

    fun onEvent(event: RecordListEvent) {
        when(event) {
            is RecordListEvent.OnDeleteRecord -> {
                viewModelScope.launch {
                    repository.deleteRecord(event.record)
                }
            }
            is RecordListEvent.OnRecordStatusChange -> {
                viewModelScope.launch {
                    checkStatus = event.status
                    repository.insertRecord(
                        ToolRecord(
                            toolRecordId = event.record.toolRecordId,
                            toolSetId = event.record.toolSetId,
                            productId = event.record.productId,
                            productName = event.record.productName,
                            roomNumber = event.record.roomNumber,
                            dosesRan = event.record.dosesRan,
                            checkStatus = checkStatus,
                            date = event.record.date,
                        )
                    )
                }
            }
        }
    }
}