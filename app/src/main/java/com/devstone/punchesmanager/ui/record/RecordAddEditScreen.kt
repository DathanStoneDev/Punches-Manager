package com.devstone.punchesmanager.ui.record

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

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
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = "Add A New Record For: ${viewModel.toolSetPONumberForRecord}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            /*
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = viewModel.toolSetPONumberForRecord,
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (viewModel.toolRecordId <= 0) {
                    Text(text = "New Record!")
                }
                Text(text = viewModel.productIdForRecord)
                Text(text = viewModel.productNameForRecord)
            }*/
            TextField(
                value = viewModel.totalDoses.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnDosesRanChange(it.toLong()))
                }
            )
            TextField(
                value = viewModel.roomNumber.toString(),
                onValueChange = {
                    viewModel.onEvent(AddEditRecordEvent.OnRoomNumberChange(it.toInt()))
                }
            )

        }

    }

}