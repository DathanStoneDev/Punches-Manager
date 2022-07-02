package com.devstone.punchesmanager.ui.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.data.entities.Product

@Composable
fun ProductItem(
    product: Product,
    onEvent: (ProductListEvent) -> Unit,
    modifier: Modifier = Modifier
){
    Surface (
        modifier = modifier
            .width(350.dp)
            .height(55.dp),
        color = Color.White,
        shape = CutCornerShape(10.dp)
            )
    {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${product.productId}: ${product.name}",
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
                    .padding(10.dp)
            )
            IconButton(onClick = {
                onEvent(ProductListEvent.OnDeleteProductClick(product))
            }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }

}