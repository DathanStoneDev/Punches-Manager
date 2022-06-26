package com.devstone.punchesmanager.ui.record

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.ui.product.ProductItem
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecordAddEditScreen(
    onPopBackStack: ()-> Unit,
    viewModel: AddEditRecordViewModel = hiltViewModel(),
    modifier: Modifier
){
    
    val products = viewModel.products.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = { AddRecordTopAppBar(onEvent = viewModel::onEvent) }
    ) {

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            var productText by remember {
                mutableStateOf("")
            }
            Row {
                Text(
                    text = "New Record: ${viewModel.toolSetPONumberForRecord}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            TextField(
                value = viewModel.totalDoses.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnDosesRanChange(it.toLong()))
                },
                label = { Text(text = "Doses: ")},
                singleLine = true
            )
            TextField(
                value = viewModel.roomNumber.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnRoomNumberChange(it.toInt()))
                },
                label = { Text(text = "Room Number")},
                singleLine = true
            )
            TextField(
                value = productText,
                onValueChange = { productText = it },
                label = { Text(text = "Products")},
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            val filteredOptions = products.value.filter { 
                it.name.contains(productText, ignoreCase = true) }
            if (filteredOptions.isNotEmpty()) {
                LazyColumn(modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)) {
                    items(filteredOptions) { product ->
                        ProductItem(
                            product = product,
                            onEvent = {},
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    productText = product.name
                                    viewModel.onEvent(AddEditRecordEvent.OnProductClick(product))
                                }
                    )
                    }
                }
                }
            }
            }

        }

@Composable
fun AddRecordTopAppBar(onEvent: (AddEditRecordEvent) -> Unit) {
    TopAppBar(
        title = { Text("Home") },
        backgroundColor = Color.LightGray,
        actions = {
            IconButton(
                onClick = {
                    onEvent(AddEditRecordEvent.OnProfileClick)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "profile",
                    tint = Color.Red
                )
            }
            IconButton(
                onClick = {
                    onEvent(AddEditRecordEvent.OnSaveRecordClick)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "profile",
                    tint = Color.Red
                )
            }
        },
        elevation = 0.dp
    )
}


