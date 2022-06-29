package com.devstone.punchesmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devstone.punchesmanager.data.dao.ProductDao
import com.devstone.punchesmanager.data.dao.RecordDao
import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.Product
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.entities.ToolSet

@Database(entities = [ToolSet::class, Product::class, ToolRecord::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toolSetDao(): ToolSetDao
    abstract fun productDao(): ProductDao
    abstract fun recordDao(): RecordDao

    companion object {
        const val DATABASE_NAME = "punches_db"
    }

}