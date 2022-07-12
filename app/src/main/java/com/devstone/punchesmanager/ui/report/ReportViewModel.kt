package com.devstone.punchesmanager.ui.report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.devstone.punchesmanager.data.repository.RecordRepository
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    recordRepo: RecordRepository,
    toolSetRepo: ToolSetRepository,
): ViewModel() {

    val records = recordRepo.getAllRecords()
    val toolSets = toolSetRepo.getToolSetReport()

    var searchText by mutableStateOf("")

    var showReport by mutableStateOf(0)

    fun onEvent(event: ReportEvent) {
        when(event) {
            is ReportEvent.OnSearch -> {
                searchText = event.text
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