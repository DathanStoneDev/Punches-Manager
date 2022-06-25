package com.devstone.punchesmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tool_sets")
data class ToolSet(
    @PrimaryKey val PONumber: String,
    @ColumnInfo(name = "tool_type") val tipType: Int,
    @ColumnInfo(name = "amount") val setAmount: Int,
    @ColumnInfo(name = "shape") val shape: String,
    @ColumnInfo(name = "life_expectancy") val lifeExpectancy: Int,
    @ColumnInfo(name = "cost") val cost: Double
) {

    fun calculateLifeExpectancy(tipType: Int, setAmount: Int): Int {
        return tipType * setAmount
    }

}