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
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.ui.report.ReportViewModel
import com.devstone.punchesmanager.ui.report.shared.ReportSearchBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ProductReport(
    toolRecords: List<ToolRecord>,
    modifier: Modifier,
    viewModel: ReportViewModel
) {

    val currentDateTime = LocalDateTime.now()
    val sdf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm", Locale.US)
    val formatted = sdf.format(currentDateTime)

    val filter = toolRecords.filter {
        it.productId.equals(viewModel.searchText.trim(), true)
    }

    val totalRuns = filter.count()
    val activeRuns = filter.count { runs -> runs.checkStatus }
    val inactiveRuns = filter.count { runs -> !runs.checkStatus }
    val totalDoses = filter.sumOf { doses -> doses.dosesRan }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ReportSearchBar(
            modifier = modifier,
            searchText = viewModel.searchText,
            labelText = "Product Search",
            placeholder = "Enter Product ID",
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
            if (filter.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Please Search For A Product.")
                }
            } else {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Product Summary",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = TextDecoration.Underline,
                    )
                    Spacer(modifier = modifier.height(30.dp))
                    ProductSummaryRow(modifier = modifier, data = "Product ID: ${filter[0].productId}")
                    ProductSummaryRow(modifier = modifier, data = "Product Name: ${filter[0].productName}")
                    ProductSummaryRow(modifier = modifier, data = "Total Number of Runs: $totalRuns")
                    ProductSummaryRow(modifier = modifier, data = "Current Number of Active Runs: $activeRuns")
                    ProductSummaryRow(modifier = modifier, data = "Current Number of Completed Runs: $inactiveRuns")
                    ProductSummaryRow(modifier = modifier, data = "Total Number of Doses: $totalDoses")
                    ProductSummaryRow(modifier = modifier, data = "Current Data As Of: $formatted")
                    }
                }
            }
        }
    }

@Composable
fun ProductSummaryRow(modifier: Modifier, data: String){
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
