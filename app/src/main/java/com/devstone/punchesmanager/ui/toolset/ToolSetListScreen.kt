package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ToolSetListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ToolSetListViewModel = hiltViewModel()
) {
    //ToolSets State
    val toolSets = viewModel.toolSets.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ToolSetListEvent.OnAddToolSetClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add")
            }
        }
            ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(toolSets.value) { toolset ->
                ToolSetItem(
                    toolSet = toolset,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            viewModel.onEvent(ToolSetListEvent.OnToolSetClick(toolset))
                        }
                )
            }
        }
    }
}
