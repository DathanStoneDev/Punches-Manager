package com.devstone.punchesmanager.ui.report

import androidx.lifecycle.ViewModel
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    val repository: ToolSetRepository
): ViewModel() {



}