package com.devstone.punchesmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devstone.punchesmanager.data.dao.ProductDao
import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.product.Product
import com.devstone.punchesmanager.data.entities.tool.ToolSet

@Database(entities = [ToolSet::class, Product::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toolSetDao(): ToolSetDao
    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "punches_db"
    }

}