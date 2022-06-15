package com.devstone.punchesmanager.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ProductAddEditScreen(
    onPopBackStack: ()-> Unit,
    viewModel: AddEditProductViewModel = hiltViewModel()
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
                viewModel.onEvent(AddEditProductEvent.OnSaveProductClick)
            }) {
                Icon(imageVector = Icons.Default.Check,
                    contentDescription = "Save")
            }
        }
            ) {
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            TextField(
                value = viewModel.productId,
                onValueChange = {
                    viewModel.onEvent(AddEditProductEvent.OnCreateProductId(it))
                },
                placeholder = {
                    Text(text = "Product ID")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(AddEditProductEvent.OnProductNameChange(it))
                },
                placeholder = {
                    Text(text = "Product Name")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

        }
    }
}