package com.devstone.punchesmanager.ui.report.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.ui.report.ReportEvent

@Composable
fun ReportSearchBar(
    modifier: Modifier,
    searchText: String,
    labelText: String,
    placeholder: String,
    onSearch: (ReportEvent) -> Unit,
){
    Surface (
        modifier = modifier
            .width(350.dp)
            .height(60.dp),
        elevation = 10.dp,
        color = Color.White,
        shape = CutCornerShape(10.dp),
    ) {

        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = searchText,
            onValueChange = {
                onSearch(ReportEvent.OnSearch(it))
            },
            label = {
                Text(
                    text = labelText,
                    fontFamily = FontFamily.Monospace
                )
            },
            placeholder = {
                Text(
                    modifier = modifier
                        .alpha(ContentAlpha.medium),
                    text = placeholder,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.body1.fontSize,
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White
            )
        )
    }
}