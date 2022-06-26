package com.devstone.punchesmanager.ui.record

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.data.entities.ToolRecord

@Composable
fun RecordItem(
    record: ToolRecord,
    onEvent: (RecordListEvent) -> Unit,
    modifier: Modifier,
    viewModel: RecordListViewModel = hiltViewModel()
) {
    Row (
        modifier = modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(
            text = record.productName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            onEvent(RecordListEvent.OnDeleteRecord(record))
        }) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Delete")
        }

        var state = record.checkStatus

        RadioButton(
            selected = state ,
            onClick = {
                state = true
                viewModel.onEvent(RecordListEvent.OnRecordStatusChange(state, record))
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green
            ),

        )
        RadioButton(
            selected = !state,
            onClick = {
                state = false
                viewModel.onEvent(RecordListEvent.OnRecordStatusChange(state, record))
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red
            )
        )
    }
}
