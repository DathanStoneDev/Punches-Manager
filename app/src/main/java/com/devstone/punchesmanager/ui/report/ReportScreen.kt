package com.devstone.punchesmanager.ui.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.ui.report.pages.DosingReport
import com.devstone.punchesmanager.ui.report.pages.LifeSpanReport
import com.devstone.punchesmanager.ui.report.pages.ProductReport

@Composable
fun ReportScreen(
    modifier: Modifier,
    viewModel: ReportViewModel = hiltViewModel(),
) {
    val records = viewModel.records.collectAsState(initial = emptyList())
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        var tab by remember { mutableStateOf(0)}
        TabRow(selectedTabIndex = tab) {
            ReportTabItems.reportTabItems.forEachIndexed {index, reportTab ->
                Tab(
                    text = { Text(text = reportTab.title)},
                    selected = tab == index,
                    onClick = {
                        tab = index
                        viewModel.showReport = tab
                    }
                )
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        when(viewModel.showReport){
            0 -> DosingReport(records.value, modifier, viewModel)
            1 -> LifeSpanReport(records.value, modifier, viewModel)
            2 -> ProductReport()
        }
    }
}


