package com.devstone.punchesmanager.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.devstone.punchesmanager.ui.toolset.ToolSetListEvent
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ProductListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event){
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
        ProductListTopAppBar(onEvent = viewModel::onEvent)
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
            .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(products.value) { product ->
                ProductItem(
                    product = product,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .padding(0.dp, 4.dp)
                        .clickable {
                            viewModel.onEvent(ProductListEvent.OnProductClick(product))
                        }
                )
            }
        }
    }
}

@Composable
fun ProductListTopAppBar(onEvent: (ProductListEvent) -> Unit) {
    TopAppBar(
        title = {
            Text("Products",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)
        },
        backgroundColor = Color.LightGray,
        actions = {
            IconButton(
                onClick = {
                    onEvent(ProductListEvent.OnAddEditProductClick)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "product",
                    tint = Color.Black
                )
            }
        },
        elevation = 0.dp
    )
}
