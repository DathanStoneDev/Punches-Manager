package com.devstone.punchesmanager.ui.report.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.ui.report.ReportEvent
import com.devstone.punchesmanager.ui.report.ReportViewModel
import com.devstone.punchesmanager.ui.report.shared.ReportSearchBar

@Composable
fun DosingReport(
    toolRecords: List<ToolRecord>,
    modifier: Modifier,
    viewModel: ReportViewModel
) {

    var doses: Long = 0

    val filter = toolRecords.filter {
        it.toolSetId.equals(viewModel.searchText, ignoreCase = true)
    }

        Column(
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
        Spacer(modifier = modifier.height(20.dp))
        Surface(
            modifier = modifier
                .height(500.dp)
                .padding(start = 5.dp, end = 5.dp),
            color = Color.White,
            shape = CutCornerShape(10.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableHeadings(modifier = modifier.width(100.dp), heading = "Record ID")
                TableHeadings(modifier = modifier.width(100.dp), heading = "Set ID")
                TableHeadings(modifier = modifier.width(100.dp), heading = "Doses")
            }

            if (filter.isNotEmpty() && viewModel.searchText != "") {
                LazyColumn(
                    modifier = modifier
                        .height(600.dp)
                        .padding(top = 20.dp),
                ){
                    items(filter) { record ->
                        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.toolRecordId.toString()
                            )
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.toolSetId
                            )
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.dosesRan.toString()
                            )
                        }
                    }
                    doses = filter.sumOf { doses -> doses.dosesRan }
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .height(600.dp)
                        .padding(top = 30.dp),
                ){
                    items(toolRecords) { record ->
                        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.toolRecordId.toString()
                            )
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.toolSetId
                            )
                            DosingItem(
                                modifier = modifier.padding(bottom = 8.dp, start = 5.dp),
                                dataPoint = record.dosesRan.toString()
                            )
                        }
                    }
                }
                doses = toolRecords.sumOf { doses -> doses.dosesRan }
            }
        }
            Text(text = "Total Doses: $doses")
        }
}

@Composable
fun DosingItem(modifier: Modifier, dataPoint: String){
    Row(
        modifier = modifier
            .width(100.dp)
            .padding(start = 3.dp, end = 3.dp),
    ) {
        Text(
            text = dataPoint,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun TableHeadings(modifier: Modifier, heading: String){
    Row(
        modifier = modifier
            .width(100.dp)
            .padding(start = 3.dp, end = 3.dp),
    ) {
        Text(
            text = heading,
            fontFamily = FontFamily.Monospace,
            textDecoration = TextDecoration.Underline
        )
    }
}