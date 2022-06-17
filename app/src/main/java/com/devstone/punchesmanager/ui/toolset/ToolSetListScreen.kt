package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ToolSetListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ToolSetListViewModel = hiltViewModel()
) {
    val toolSets = viewModel.toolSets.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }
    Scaffold (
        content = {
            SearchBar(
                modifier = Modifier,
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(0.dp, 75.dp)
            ) {
                if (viewModel.searchText.isBlank()) {
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
                } else {
                    items(toolSets.value) { toolset->
                        if(toolset.PONumber == viewModel.searchText) {
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ToolSetListEvent.OnAddToolSetClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add")
            }
        }
            )
}

@Composable
fun SearchBar(
    modifier: Modifier,
    viewModel: ToolSetListViewModel = hiltViewModel()
){

    Surface (
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .padding(20.dp, 15.dp, 20.dp, 0.dp),
        elevation = 10.dp,
        color = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(25)
            ){

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = viewModel.searchText,
            onValueChange = {
                viewModel.onEvent(ToolSetListEvent.OnSearchToolSet(it))
            },
            placeholder = {
                Text(
                    modifier = modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search...",
                    color = White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = White
            )
        )

    }
}
