package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        ToolSetAddEditTopAppBar(modifier = Modifier, onEvent = viewModel::onEvent)
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
                value = viewModel.PONumber,
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnCreatePONumber(it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                label = {
                    Text(
                        text = "PO Number",
                        fontFamily = FontFamily.Monospace
                    )
                },
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
                value = viewModel.life.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnLifeExpectancyChange(it.toInt()))
                },
                label = {
                    Text(
                        text = "Life Expectancy",
                        fontFamily = FontFamily.Monospace
                    )
                },
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
        ){
            TextField(
            value = viewModel.amount.toString(),
            onValueChange = {
                viewModel.onEvent(AddEditToolSetEvent.OnAmountChange(it.toInt()))
            },
            label = {
                Text(
                    text = "Number Of Punches",
                    fontFamily = FontFamily.Monospace
                )
            },
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
        ){
            TextField(
                value = viewModel.tipType.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnTipTypeChange(it.toInt()))
                },
                label = {
                    Text(
                        text = "Tip Type",
                        fontFamily = FontFamily.Monospace
                    )
                },
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
        ){
            TextField(
                value = viewModel.shape,
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnShapeChange(it))
                },
                label = {
                    Text(
                        text = "Shape",
                        fontFamily = FontFamily.Monospace
                    )
                },
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
        ){
            TextField(
                value = viewModel.cost.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditToolSetEvent.OnCostChange(it.toDouble()))
                },
                label = {
                    Text(
                        text = "Cost of Set",
                        fontFamily = FontFamily.Monospace
                    )
                },
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
fun ToolSetAddEditTopAppBar(modifier: Modifier, onEvent: (AddEditToolSetEvent) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Add/Edit ToolSet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        },
        backgroundColor = Color.LightGray,
        actions =  {
            IconButton(
                onClick = {
                    onEvent(AddEditToolSetEvent.OnSaveToolSetClick) },
                modifier = modifier
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "save",
                    tint = Color.Black,
                )
            }
        },
        elevation = 0.dp
    )
}
