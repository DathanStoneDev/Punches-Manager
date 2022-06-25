package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.data.entities.ToolSet

@Composable
fun ToolSetItem(
    toolSet: ToolSet,
    onEvent: (ToolSetListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        elevation = 10.dp,
        color = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(45)
    ) {
        Row (
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = toolSet.PONumber,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                onEvent(ToolSetListEvent.OnDeleteToolSetClick(toolSet))
            }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete")
            }
            IconButton(onClick = {
                onEvent(ToolSetListEvent.OnCreateRecordClick(toolSet.PONumber))
            }) {
                Icon(imageVector = Icons.Filled.Album,
                    contentDescription = "Delete")
            }
        }
    }

}
