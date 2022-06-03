package com.devstone.punchesmanager.data.entities.record

import androidx.room.Entity
import com.devstone.punchesmanager.data.entities.product.Product
import com.devstone.punchesmanager.data.entities.tool.ToolSet

@Entity
data class ToolRecord (
    val toolSet: ToolSet,
    val product: Product,
    val roomNumber: Int,
    var checkStatus: Boolean = false
    ) {

}