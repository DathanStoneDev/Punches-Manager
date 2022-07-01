package com.devstone.punchesmanager.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ProductAddEditScreen(
    onPopBackStack: ()-> Unit,
    viewModel: AddEditProductViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductAddEditTopAppBar(onEvent = viewModel::onEvent)
        Spacer(modifier = Modifier.height(40.dp))
        Surface(
            modifier = Modifier
                .width(250.dp)
                .height(55.dp),
            elevation = 10.dp,
            color = Color.White,
            shape = CutCornerShape(10.dp)
        ) {
            TextField(
                value = viewModel.productId,
                onValueChange = {
                    viewModel.onEvent(AddEditProductEvent.OnCreateProductId(it))
                },
                label = { Text(text = "Product ID",
                    fontFamily = FontFamily.Monospace) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = Modifier
                .width(250.dp)
                .height(55.dp),
            elevation = 10.dp,
            color = Color.White,
            shape = CutCornerShape(10.dp)
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(AddEditProductEvent.OnProductNameChange(it))
                },
                label = { Text(text = "Product Name",
                    fontFamily = FontFamily.Monospace) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )
        }
    }
}



            


@Composable
fun ProductAddEditTopAppBar(onEvent: (AddEditProductEvent) -> Unit) {
    TopAppBar(
        title = {
            Text("Add/Edit Product",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)
        },
        backgroundColor = Color.LightGray,
        actions = {
            IconButton(
                onClick = {
                    onEvent(AddEditProductEvent.OnSaveProductClick)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "profile",
                    tint = Color.Black
                )
            }
        },
        elevation = 0.dp
    )
}