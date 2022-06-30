package com.devstone.punchesmanager.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
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
) {

    val products = viewModel.products.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddRecordTopAppBar(onEvent = viewModel::onEvent)
        Spacer(modifier = modifier.height(40.dp))
        var productText by remember {
            mutableStateOf("")
        }

        Text(
            text = "Tool Set ID: ${viewModel.toolSetPONumberForRecord}",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 2.sp,
            modifier = modifier
                .padding(bottom = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = modifier
                .width(250.dp)
                .height(55.dp),
            elevation = 10.dp,
            color = White,
            shape = CutCornerShape(10.dp)
        ) {
            TextField(
                value = viewModel.totalDoses.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnDosesRanChange(it.toLong()))
                },
                label = { Text(text = "Doses: ",
                    fontFamily = FontFamily.Monospace) },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = White
            ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = modifier
                .width(250.dp)
                .height(55.dp),
            elevation = 10.dp,
            color = White,
            shape = CutCornerShape(10.dp)
        ) {
            TextField(
                value = viewModel.roomNumber.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnRoomNumberChange(it.toInt()))
                },
                label = { Text(text = "Room Number",
                    fontFamily = FontFamily.Monospace) },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = White
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = modifier
                .width(250.dp)
                .height(55.dp),
            elevation = 10.dp,
            color = White,
            shape = CutCornerShape(10.dp)
        ){
            TextField(
                value = productText,
                onValueChange = { productText = it },
                label = { Text(text = "Products",
                    fontFamily = FontFamily.Monospace) },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = White
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        val filteredOptions = products.value.filter {
            it.name.contains(productText, ignoreCase = true)
        }
        if (filteredOptions.isNotEmpty() && productText != "") {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ) {
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

@Composable
fun AddRecordTopAppBar(onEvent: (AddEditRecordEvent) -> Unit) {
    TopAppBar(
        title = {
            Text("Add Record",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)
                },
        backgroundColor = Color.LightGray,
        actions = {
            IconButton(
                onClick = {
                    onEvent(AddEditRecordEvent.OnSaveRecordClick)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "profile",
                    tint = Black
                )
            }
        },
        elevation = 0.dp
    )
}


