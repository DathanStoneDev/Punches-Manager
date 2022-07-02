package com.devstone.punchesmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey
    val productId: String,
    @ColumnInfo(name = "name") val name: String,
        ){
}