package com.devstone.punchesmanager.ui.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ReportScreen(
    modifier: Modifier,
    viewModel: ReportViewModel = hiltViewModel(),
) {
    val data = viewModel.data
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        when(viewModel.showReport){
            1 -> DosingReport()
            2 -> LifeSpanReport()
            3 -> ProductReport()
        }
    }
}