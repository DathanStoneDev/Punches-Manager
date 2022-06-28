package com.devstone.punchesmanager.ui.record

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

    var expanded by remember { mutableStateOf(false) }

    val increasedSize by animateDpAsState(
        if (expanded) 48.dp else 16.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column {
            Row(
                modifier = modifier
                    .selectableGroup()
                    .padding(top = 16.dp, bottom = increasedSize),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(onClick = {
                    expanded = !expanded
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "details",
                    )
                }
                Text(
                    text = record.toolSetId,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    onEvent(RecordListEvent.OnDeleteRecord(record))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }

                var state = record.checkStatus

                RadioButton(
                    selected = state,
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
            if (expanded){
                Column(

                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Product ID: ${record.productId}")
                    Text(text = "Product Name: ${record.productName}")
                    Text(text = "Doses Ran: ${record.dosesRan}")
                }
            }

        }
    }


}
