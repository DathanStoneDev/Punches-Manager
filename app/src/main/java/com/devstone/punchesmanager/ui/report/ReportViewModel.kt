package com.devstone.punchesmanager.ui.report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.repository.RecordRepository
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import com.devstone.punchesmanager.ui.report.model.ToolSetReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val recordRepo: RecordRepository
): ViewModel() {

    val records = recordRepo.getAllRecords()

    var searchText by mutableStateOf("")
        private set

    var showReport by mutableStateOf(0)

    fun onEvent(event: ReportEvent) {
        when(event) {
            is ReportEvent.OnSearchToolSetByPO -> {
                searchText = event.po
            }
            is ReportEvent.OnClickDosingReport -> {
                showReport = 0
            }
            is ReportEvent.OnClickProductReport -> {
                showReport = 1
            }
            is ReportEvent.OnClickLifeSpanReport -> {
                showReport = 2
            }
        }
    }
}