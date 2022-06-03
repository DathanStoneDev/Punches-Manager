package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect


@Composable
fun ToolSetAddEditScreen(
    onPopBackStack: ()-> Unit,
    viewModel: AddEditToolSetViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditToolSetEvent.OnSaveToolSetClick)
            }) {
                Icon(imageVector = Icons.Default.Check,
                contentDescription = "Save")

            }
        }
            ){
        Column (
            modifier = Modifier.fillMaxSize()
                )
        {
            TextField(
                value = viewModel.PONumber,
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnCreatePONumber(it))
                },
                placeholder = {
                    Text(text = "PONumber")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            TextField(
                value = viewModel.life.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnLifeExpectancyChange(it.toInt()))
                },
                placeholder = {
                    Text(text = "Life Expectancy (Integer)")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            TextField(
                value = viewModel.amount.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnAmountChange(it.toInt()))
                },
                placeholder = {
                    Text(text = "amount")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            TextField(
                value = viewModel.tipType.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnTipTypeChange(it.toInt()))
                },
                placeholder = {
                    Text(text = "Shape")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            TextField(
                value = viewModel.shape,
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnShapeChange(it))
                },
                placeholder = {
                    Text(text = "Shape")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.cost.toString(), 
                onValueChange = {
                viewModel.onEvent(AddEditToolSetEvent.OnCostChange(it.toDouble()))
            },
                placeholder = {
                    Text(text = "Cost: Use at least 1 decimal place.")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
                )
            }
        }
    }