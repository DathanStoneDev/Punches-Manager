package com.devstone.punchesmanager.ui.toolset

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
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
            .width(350.dp)
            .height(55.dp),
        color = White,
        shape = CutCornerShape(10.dp)
    ) {
        Row (
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = toolSet.PONumber,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
                    .padding(start = 10.dp)
            )
            IconButton(onClick = {
                onEvent(ToolSetListEvent.OnCreateRecordClick(toolSet.PONumber))
            }) {
                Icon(imageVector = Icons.Filled.NoteAdd,
                    contentDescription = "Add Record")
            }
            IconButton(onClick = {
                onEvent(ToolSetListEvent.OnDeleteToolSetClick(toolSet))
            }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete")
            }
        }
    }

}
