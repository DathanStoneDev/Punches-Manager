package com.devstone.punchesmanager.ui.report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    val repository: ToolSetRepository
): ViewModel() {

    val data = repository.getToolSetReport()

    var searchText by mutableStateOf("")
        private set

    var showReport by mutableStateOf(0)
        private set

    fun onEvent(event: ReportEvent) {
        when(event) {
            is ReportEvent.OnSearchToolSetByPO -> {
                searchText = event.po
            }
            is ReportEvent.OnClickDosingReport -> {
                showReport = 1
            }
            is ReportEvent.OnClickProductReport -> {
                showReport = 2
            }
            is ReportEvent.OnClickLifeSpanReport -> {
                showReport = 3
            }
        }
    }
}