package com.devstone.punchesmanager.ui.report.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.ui.report.ReportViewModel
import com.devstone.punchesmanager.ui.report.model.ToolSetReport
import com.devstone.punchesmanager.ui.report.shared.ReportSearchBar
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun LifeSpanReport(
    toolSetReport: List<ToolSetReport>,
    modifier: Modifier,
    viewModel: ReportViewModel
) {
    val currentDateTime = LocalDateTime.now()
    val sdf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm", Locale.US)
    val formatted = sdf.format(currentDateTime)

    var totalDoses: Long = 0
    var lifeExpectancy = 0
    val retiredStatusMap = toolSetReport.associate { it.toolSet.PONumber to it.toolSet.retired }
    val lifeExpectancyMap = toolSetReport.associate { it.toolSet.PONumber to it.toolSet.lifeExpectancy }
    var filteredLifeMap: Map<String, Int> = emptyMap()
    val toolRecordsMap = toolSetReport.associate { it.toolSet.PONumber to it.toolRecords }

    fun calculateTotalDoses(id: String) {
       toolRecordsMap[id]?.forEach { toolRecord -> totalDoses += toolRecord.dosesRan }
    }

    fun filterMap(id: String) {
        filteredLifeMap = lifeExpectancyMap.filterKeys { it.contains(id)  }
    }

    fun getLifeExpectancy(id: String) {
        lifeExpectancy = filteredLifeMap[id]!!
    }

    fun calculateLifePercentage(): String? {
        val df = DecimalFormat("0.00")
        return df.format((totalDoses.toFloat()/lifeExpectancy.toFloat() * 100))
    }

    fun getRetiredStatus(id: String): String {
        return if (retiredStatusMap[id] == true){
            "Active"
        } else {
            "Retired"
        }
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
                .height(350.dp)
                .padding(start = 5.dp, end = 5.dp),
            color = Color.White,
            shape = CutCornerShape(10.dp)
        ) {
            filterMap(viewModel.searchText)
            if (!filteredLifeMap.containsKey(viewModel.searchText)) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Please Search For A Tool Set.")
                }
            } else {
                calculateTotalDoses(viewModel.searchText)
                getLifeExpectancy(viewModel.searchText)
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Life Expectancy Summary",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = TextDecoration.Underline,
                    )
                    Spacer(modifier = modifier.height(30.dp))
                    LifeSpanSummaryRow(modifier = modifier, data = "PO#: ${filteredLifeMap.keys}")
                    LifeSpanSummaryRow(modifier = modifier, data = "Life Expectancy: $lifeExpectancy")
                    LifeSpanSummaryRow(modifier = modifier, data = "Total Doses Ran: $totalDoses")
                    LifeSpanSummaryRow(modifier = modifier, data = "Percent of Life Used: ${calculateLifePercentage()}%")
                    LifeSpanSummaryRow(modifier = modifier, data = "Status: ${getRetiredStatus(viewModel.searchText)}")
                    LifeSpanSummaryRow(modifier = modifier, data = "Current Data As Of: $formatted")
                }
            }
        }
    }
}

@Composable
fun LifeSpanSummaryRow(modifier: Modifier, data: String){
    Row {
        Text(
            text = data,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            modifier = modifier
                .weight(1f)
                .padding(5.dp)
        )
    }
}


