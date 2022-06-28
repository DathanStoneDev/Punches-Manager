package com.devstone.punchesmanager.ui.record

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Scaffold (
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            ) {
                items(records.value) { record ->
                    RecordItem(
                        record = record,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .clickable {
                                viewModel.onEvent(RecordListEvent.OnRecordClick(record))
                            }
                    )
                }
            }
        }
    )

}