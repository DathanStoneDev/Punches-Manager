package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.ui.home.HomeEvent
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ToolSetTopAppBar(modifier = Modifier, onEvent = viewModel::onEvent)
        Spacer(modifier = Modifier.height(15.dp))
        SearchBar(
            modifier = Modifier,
            searchText = viewModel.searchText,
        ) {
            viewModel.onEvent(ToolSetListEvent.OnSearchToolSet(it))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
                .background(LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.searchText.isBlank()) {
                items(toolSets.value, key = {toolset -> toolset.PONumber}) { toolset ->
                    ToolSetItem(
                        toolSet = toolset,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .padding(0.dp, 4.dp)
                            .clickable {
                                viewModel.onEvent(ToolSetListEvent.OnToolSetClick(toolset))
                            }
                    )
                }
            } else {
                //need to filter to ignore case
                items(toolSets.value) { toolset->
                    if(toolset.PONumber == viewModel.searchText) {
                        ToolSetItem(
                            toolSet = toolset,
                            onEvent = viewModel::onEvent,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(ToolSetListEvent.OnToolSetClick(toolset))
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit
){

    Surface (
        modifier = modifier
            .width(350.dp)
            .height(60.dp),
        elevation = 10.dp,
        color = White,
        shape = CutCornerShape(10.dp),
            ){

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = searchText,
            onValueChange = {
                onSearchTextChange(it)
            },
            label = { Text(text = "Tool Set Search",
                fontFamily = FontFamily.Monospace)},
            placeholder = {
                Text(
                    modifier = modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search By PO Number...",
                    color = Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.body1.fontSize,
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = White
            )
        )

    }
}

@Composable
fun ToolSetTopAppBar(modifier: Modifier, onEvent: (ToolSetListEvent) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Tool Sets",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
                },
        backgroundColor = LightGray,
        actions =  {
            IconButton(
                onClick = {
                onEvent(ToolSetListEvent.OnAddToolSetClick)
            }, modifier = modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Tool Set",
                    tint = Black
                )

            }
        },
        elevation = 0.dp
    )
}
