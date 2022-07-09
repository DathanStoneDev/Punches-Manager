package com.devstone.punchesmanager.ui.report.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.ui.report.ReportEvent
import com.devstone.punchesmanager.ui.report.ReportViewModel
import com.devstone.punchesmanager.ui.report.shared.ReportSearchBar

@Composable
fun LifeSpanReport(
    toolRecords: List<ToolRecord>,
    modifier: Modifier,
    viewModel: ReportViewModel
) {

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReportSearchBar(
                    modifier = modifier,
                    searchText = viewModel.searchText,
                    labelText = "Tool Set Search",
                    placeholder = "Enter Tool Set ID",
                    onSearch = viewModel::onEvent
                )
    }




}
