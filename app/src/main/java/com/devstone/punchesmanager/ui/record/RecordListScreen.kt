package com.devstone.punchesmanager.ui.record


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun RecordListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: RecordListViewModel = hiltViewModel()
) {
    val records = viewModel.records.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecordTopAppBar()
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(records.value) { record ->
                Spacer(modifier = Modifier.height(4.dp))
                RecordItem(
                    record = record,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                )
            }
        }
    }

}

@Composable
fun RecordTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Records",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        },
        backgroundColor = Color.LightGray,
        elevation = 0.dp
    )
}