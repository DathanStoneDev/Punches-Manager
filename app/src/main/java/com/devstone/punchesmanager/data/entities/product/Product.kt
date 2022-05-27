package com.devstone.punchesmanager.data.entities.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey val productId: Int,
    @ColumnInfo(name = "name") val name: String,
        ){
}